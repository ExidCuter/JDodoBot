package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IServerService;
import xyz.the_dodo.database.types.Server;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ServerController {
    @Autowired
    private IServerService serverService;

    @RequestMapping(value = "/servers", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<Server> servers;

        servers = serverService.findAll();

        return ResponseEntity.ok(servers);
    }

    @RequestMapping(value = "/server/{discordId}", method = RequestMethod.GET)
    public ResponseEntity getById(@PathVariable String discordId) {
        Server server;

        server = serverService.findByDiscordId(discordId);

        return ResponseEntity.ok(server);
    }
}
