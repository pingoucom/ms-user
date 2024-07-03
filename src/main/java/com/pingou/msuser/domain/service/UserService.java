package com.pingou.msuser.domain.service;

import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.domain.exception.EmailIsTakenException;
import com.pingou.msuser.domain.exception.UserNotFoundException;
import com.pingou.msuser.domain.exception.IncorrectPasswordException;
import com.pingou.msuser.domain.hash.Hasher;
import com.pingou.msuser.domain.repository.TokenRepository;
import com.pingou.msuser.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Hasher hasher;

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    public UserService(Hasher hasher, TokenRepository tokenRepository, UserRepository userRepository) {
        this.hasher = hasher;
        this.tokenRepository = tokenRepository;
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

    public User signUp(String name, String email, String password) throws EmailIsTakenException {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);

        boolean isEmailTaken = userRepository.existsByEmail(newUser.getEmail());

        if (isEmailTaken) {
            throw new EmailIsTakenException();
        }

        newUser.setPassword(hasher.hash(newUser.getPassword()));
        newUser = userRepository.save(newUser);

        newUser.setConsumerId(tokenRepository.createConsumer(newUser));
        newUser = userRepository.save(newUser);

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

    public void delete(String id) throws UserNotFoundException {
        User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(user.getId());
    }
}
