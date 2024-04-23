package tr.com.trs.customerbatch.government.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import tr.com.trs.customerbatch.dal.model.Customer;
import tr.com.trs.customerbatch.dal.repository.CustomerRepository;

import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CustomerReader implements ItemReader<Customer>, StepExecutionListener {

    private final CustomerRepository customerRepository;
    private Iterator<Customer> musteriIterator;
    @Override
    public Customer read() throws Exception {
        if (musteriIterator == null) {
            musteriIterator = customerRepository.findAll().iterator();
        }
        return musteriIterator.hasNext() ? musteriIterator.next() : null;
    }


    @Override
    public void beforeStep(StepExecution stepExecution) {

    }


}