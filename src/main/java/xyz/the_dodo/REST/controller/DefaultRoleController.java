package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IDefaultRoleService;
import xyz.the_dodo.database.types.DefaultRole;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class DefaultRoleController {
    @Autowired
    private IDefaultRoleService defaultRoleService;

    @RequestMapping(value = "/defaultRoles", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<DefaultRole> defaultRoles;

        defaultRoles = defaultRoleService.findAll();

        return ResponseEntity.ok(defaultRoles);
    }

    @RequestMapping(value = "/defaultRole/{discordId}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String discordId) {
        DefaultRole defaultRole;

        defaultRole = defaultRoleService.findByServerId(1L);

        return ResponseEntity.ok(defaultRole);
    }
}
