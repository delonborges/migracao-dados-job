package com.delon.migracao_dados_job.step;

import com.delon.migracao_dados_job.domain.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigracaoDadosBancariosStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public MigracaoDadosBancariosStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step migracaoDadosBancariosStep(ItemReader<DadosBancarios> arquivoDadosBancariosReader, ItemWriter<DadosBancarios> bancoDadosBancariosWriter) {
        return new StepBuilder("migracaoDadosBancariosStep", jobRepository).<DadosBancarios, DadosBancarios>chunk(10000, transactionManager)
                                                                           .reader(arquivoDadosBancariosReader)
                                                                           .writer(bancoDadosBancariosWriter)
                                                                           .build();
    }
}
