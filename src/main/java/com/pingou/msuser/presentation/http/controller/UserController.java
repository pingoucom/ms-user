package com.pingou.msuser.presentation.http.controller;

import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.domain.service.UserService;
import com.pingou.msuser.presentation.http.response.UserResponse;
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
    public UserResponse show(@PathVariable String id) {
        User user = userService.find(id);

        return new UserResponse(user);
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
