package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AdminController
{
	@Autowired
	private IAdminService m_adminService;

	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public ResponseEntity getAllUsers() {
		List<Admin> admins;

		admins = m_adminService.findAll();

		return ResponseEntity.ok(admins);
	}

	@RequestMapping(value = "/admin/{discordId}", method = RequestMethod.GET)
	public ResponseEntity getUserById(@PathVariable String discordId) {
		List<Admin> admin;

		admin = m_adminService.getAdminsByServerId(discordId);

		return ResponseEntity.ok(admin);
	}
}
