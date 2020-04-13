package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IAdminService;
import xyz.the_dodo.database.types.Admin;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class AdminController {
	@Autowired
	private IAdminService adminService;

	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public ResponseEntity getAllAdmins() {
		List<Admin> admins;

		admins = adminService.findAll();

		return ResponseEntity.ok(admins);
	}

	@RequestMapping(value = "/admin/{discordId}", method = RequestMethod.GET)
	public ResponseEntity getAdminById(@PathVariable String discordId) {
		List<Admin> admin;

		admin = adminService.getAdminsByServerId(discordId);

		return ResponseEntity.ok(admin);
	}
}
