package tr.com.trs.customerbatch.government.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import tr.com.trs.customerbatch.government.job.CustomerProcessor;
import tr.com.trs.customerbatch.government.job.CustomerReader;
import tr.com.trs.customerbatch.government.job.CustomerWriter;
import tr.com.trs.customerbatch.government.listerners.JobEdevletCompletionListener;
import tr.com.trs.customerbatch.government.listerners.SkipPolicyListener;
import tr.com.trs.customerbatch.dal.model.Customer;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class JobConfiguration {
    private final JobRepository jobRepository;
    private static final int BATCH_SIZE = 5;
    private final CustomerProcessor processor;
    private final CustomerReader reader;
    private final JobEdevletCompletionListener listener;

    private final SkipPolicyListener skipPolicyListener;
    private final CustomerWriter writer;
    private final PlatformTransactionManager batchTransactionManager;

    @Bean
    public Job edevletJob(JobRepository jobRepository, JobEdevletCompletionListener listener, Step step1) {
        return new JobBuilder("edevletJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }



//    @Bean
//    public JdbcBatchItemWriter<Musteri> writer(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Musteri>().itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("Update musteri Set izin=true where id=:id")
//                .dataSource(dataSource)
//                .build();
//    }
    @Bean
    public Step step1() {
        return new StepBuilder("first step", jobRepository)
                .<Customer, Customer>chunk(BATCH_SIZE, batchTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skipPolicy(skipPolicyListener)
                .build();
    }


}
