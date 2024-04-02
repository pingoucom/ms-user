package com.pingou.msuser.presentation.http.controller;

import com.pingou.msuser.application.dto.UserUpdateDTO;
import com.pingou.msuser.domain.entity.User;
import com.pingou.msuser.application.dto.UserDTO;
import com.pingou.msuser.domain.service.UserService;
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
    public List<UserDTO> index() {
        List<User> users = (List<User>) userService.findAll();

        return users.stream().map(UserDTO::new).toList();
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDTO show(@PathVariable String id) {
        User user = userService.find(id);

        return new UserDTO(user);
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public UserDTO update(@PathVariable String id, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        User updatedUser = userService.update(id, userUpdateDTO.toUser());

        return new UserDTO(updatedUser);
    }

    @ResponseBody
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.delete(id);
    }
}
