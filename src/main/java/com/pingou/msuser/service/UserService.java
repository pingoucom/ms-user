package com.pingou.msuser.service;

import com.pingou.msuser.entity.User;
import com.pingou.msuser.exception.EmailIsTakenException;
import com.pingou.msuser.exception.IncorrectPasswordException;
import com.pingou.msuser.exception.UserNotFoundException;
import com.pingou.msuser.hash.Hasher;
import com.pingou.msuser.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Hasher hasher;

    private final UserRepository userRepository;

    public UserService(Hasher hasher, UserRepository userRepository) {
        this.hasher = hasher;
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

        return userRepository.save(newUser);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User find(Long id) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    public User update(Long id, User updatedUser) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .map(u -> {
                    u.setName(updatedUser.getName());
                    u.setEmail(updatedUser.getEmail());

                    return userRepository.save(u);
                })
                .orElseThrow(UserNotFoundException::new);
    }

    public void delete(Long id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }

        userRepository.deleteById(id);
    }
}
