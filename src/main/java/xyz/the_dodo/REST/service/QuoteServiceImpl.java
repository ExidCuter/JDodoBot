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
    private IQuoteRepo m_quoteRepo;

    public void setQuoteRepo(IQuoteRepo p_quoteRepo)
    {
        m_quoteRepo = p_quoteRepo;
    }

    @Override
    public Quote findById(long id) {
        return m_quoteRepo.getOne(id);
    }

    @Override
    public List<Quote> findAll() {
        return m_quoteRepo.findAll();
    }

    @Override
    public Quote save(Quote quote) {
        if(quote != null)
            return m_quoteRepo.save(quote);

        return null;
    }

    @Override
    public Quote save(Quote oldObject, Quote newObject)
    {
        if(oldObject != null && newObject != null && oldObject.getId() == newObject.getId())
            return m_quoteRepo.save(newObject);

        return null;
    }

    @Override
    public boolean delete(Quote quote) {
        if(quote != null){
            m_quoteRepo.deleteById(quote.getId());
            return true;
        }

        return false;
    }
}
