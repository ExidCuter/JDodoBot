package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IUserService;
import xyz.the_dodo.database.types.User;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity getAllUsers() {
        List<User> users;

        users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @RequestMapping(value = "/user/{discordId}", method = RequestMethod.GET)
    public ResponseEntity getUserById(@PathVariable String discordId) {
        User user;

        user = userService.findByDiscordId(discordId);

        return ResponseEntity.ok(user);
    }

}
