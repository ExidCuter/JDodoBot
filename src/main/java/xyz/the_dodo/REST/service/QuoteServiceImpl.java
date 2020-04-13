package xyz.the_dodo.REST.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.the_dodo.database.interfaces.repos.IQuoteRepo;
import xyz.the_dodo.database.interfaces.services.IQuoteService;
import xyz.the_dodo.database.types.Quote;

import java.util.List;

@Service
public class QuoteServiceImpl implements IQuoteService {
    @Autowired
    private IQuoteRepo quoteRepo;

    public void setQuoteRepo(IQuoteRepo quoteRepo) {
        this.quoteRepo = quoteRepo;
    }

    @Override
    public Quote findById(long id) {
        return quoteRepo.getOne(id);
    }

    @Override
    public List<Quote> findAll() {
        return quoteRepo.findAll();
    }

    @Override
    public Quote save(Quote quote) {
        if (quote != null)
            return quoteRepo.save(quote);

        return null;
    }

    @Override
    public boolean delete(Quote quote) {
        if (quote != null) {
            quoteRepo.deleteById(quote.getId());
            return true;
        }

        return false;
    }
}
