package tr.com.trs.customerbatch.government.job;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import tr.com.trs.customerbatch.dal.model.Customer;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer>, StepExecutionListener {
    @Override
    public Customer process(Customer item) throws Exception {
        System.out.println(item.toString());
        return item;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        StepExecutionListener.super.beforeStep(stepExecution);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return StepExecutionListener.super.afterStep(stepExecution);
    }
}
