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
public class DefaultRoleController
{
	@Autowired
	private IDefaultRoleService m_defaultRoleService;

	@RequestMapping(value = "/defaultRoles", method = RequestMethod.GET)
	public ResponseEntity getAllUsers() {
		List<DefaultRole> defaultRoles;

		defaultRoles = m_defaultRoleService.findAll();

		return ResponseEntity.ok(defaultRoles);
	}

	@RequestMapping(value = "/defaultRole/{discordId}", method = RequestMethod.GET)
	public ResponseEntity getUserById(@PathVariable String discordId) {
		DefaultRole defaultRole;

		defaultRole = m_defaultRoleService.findByServerId(1L);

		return ResponseEntity.ok(defaultRole);
	}
}
