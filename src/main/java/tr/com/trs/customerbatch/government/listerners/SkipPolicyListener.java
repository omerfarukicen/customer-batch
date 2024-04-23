package tr.com.trs.customerbatch.government.listerners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.stereotype.Component;
import tr.com.trs.customerbatch.dal.model.Customer;

@Component
@Slf4j
public class SkipPolicyListener implements SkipPolicy {
    @OnSkipInRead
    public void onSkipInRead(Throwable throwable) {
        log.error("Read Error:" + throwable.getMessage());
    }

    @OnSkipInWrite
    public void onSkipInWrite(Customer customer, Throwable throwable) {
        log.error("Write Error:" + throwable.getMessage());
    }
    /*
     * Bazı caselerde skip edebiliriz
     * */

    @Override
    public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {
        return false;
    }
}
