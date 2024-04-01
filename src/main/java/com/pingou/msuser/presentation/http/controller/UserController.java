package com.pingou.msuser.presentation.http.controller;

import com.pingou.msuser.dto.SignInDTO;
import com.pingou.msuser.dto.SignUpDTO;
import com.pingou.msuser.dto.UserUpdateDTO;
import com.pingou.msuser.entity.User;
import com.pingou.msuser.presentation.http.response.UserResponse;
import com.pingou.msuser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public final class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserResponse> index() {
        List<User> users = (List<User>) userService.findAll();

        return users.stream().map(UserResponse::new).toList();
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserResponse show(@PathVariable Long id) {
        User user = userService.find(id);

        return new UserResponse(user);
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        User updatedUser = userService.update(id, userUpdateDTO.toUser());

        return new UserResponse(updatedUser);
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
