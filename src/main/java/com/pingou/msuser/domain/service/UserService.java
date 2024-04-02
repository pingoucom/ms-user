package com.pingou.msuser.domain.service;

import com.pingou.msuser.domain.broker.producer.UserProducer;
import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.domain.exception.EmailIsTakenException;
import com.pingou.msuser.domain.exception.UserNotFoundException;
import com.pingou.msuser.domain.exception.IncorrectPasswordException;
import com.pingou.msuser.domain.hash.Hasher;
import com.pingou.msuser.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Hasher hasher;

    private final UserProducer userProducer;

    private final UserRepository userRepository;

    public UserService(Hasher hasher, UserProducer userProducer, UserRepository userRepository) {
        this.hasher = hasher;
        this.userProducer = userProducer;
        this.userRepository = userRepository;
    }

    public User signIn(String email, String plainPassword) throws UserNotFoundException, IncorrectPasswordException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (!hasher.verify(plainPassword, user.getPassword())) {
            throw new IncorrectPasswordException();
        }

        return user;
    }

    public User signUp(User newUser) throws EmailIsTakenException {
        boolean isEmailTaken = userRepository.existsByEmail(newUser.getEmail());

        if (isEmailTaken) {
            throw new EmailIsTakenException();
        }

        newUser.setPassword(hasher.hash(newUser.getPassword()));
        newUser = userRepository.save(newUser);

        userProducer.produceUserCreationMessage(newUser);

        return newUser;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User find(String id) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User update(String id, User updatedUser) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .map(u -> {
                    u.setName(updatedUser.getName());
                    u.setEmail(updatedUser.getEmail());

                    return userRepository.save(u);
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public void delete(String id) throws UserNotFoundException {
        User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(user.getId());
        userProducer.produceUserDeletionMessage(user);
    }
}
