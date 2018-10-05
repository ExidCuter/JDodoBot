package xyz.the_dodo.REST.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.the_dodo.database.interfaces.services.IQuoteService;
import xyz.the_dodo.database.types.Quote;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class QuoteController
{
	@Autowired
	private IQuoteService m_quoteService;

	@RequestMapping(value = "/quotes", method = RequestMethod.GET)
	public ResponseEntity getAll() {
		List<Quote> quotes;

		quotes = m_quoteService.findAll();

		return ResponseEntity.ok(quotes);
	}

	@RequestMapping(value = "/quote/{user}", method = RequestMethod.GET)
	public ResponseEntity getById(@PathVariable String user) {
		List<Quote> quotes;

		quotes = m_quoteService.findAll();

		return ResponseEntity.ok(quotes);
	}
}
