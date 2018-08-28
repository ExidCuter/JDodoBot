package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IBugService;
import xyz.the_dodo.database.types.BugReport;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BugController
{
	@Autowired
	private IBugService m_bugService;

	@RequestMapping(value = "/bugs", method = RequestMethod.GET)
	public ResponseEntity getAllUsers() {
		List<BugReport> bugReports;

		bugReports = m_bugService.findAll();

		return ResponseEntity.ok(bugReports);
	}
}
