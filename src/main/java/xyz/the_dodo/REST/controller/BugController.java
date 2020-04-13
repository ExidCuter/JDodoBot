package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.the_dodo.database.interfaces.services.IBugService;
import xyz.the_dodo.database.types.BugReport;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class BugController {
    @Autowired
    private IBugService bugService;

    @RequestMapping(value = "/bugs", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<BugReport> bugReports;

        bugReports = bugService.findAll();

        return ResponseEntity.ok(bugReports);
    }
}
