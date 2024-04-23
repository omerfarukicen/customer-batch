package tr.com.trs.customerbatch.government.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import tr.com.trs.customerbatch.dal.model.Customer;
import tr.com.trs.customerbatch.dal.repository.CustomerRepository;

@Component
@RequiredArgsConstructor
public class CustomerWriter implements ItemWriter<Customer> {
    private final CustomerRepository customerRepository;

    @Override
    public void write(Chunk<? extends Customer> customerList) throws Exception {
        for (Customer customer : customerList) {
            customer.setPermit(true);
        }

        customerRepository.saveAll(customerList);
    }
}