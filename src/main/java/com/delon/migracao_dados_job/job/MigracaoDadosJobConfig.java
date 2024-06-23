package com.delon.migracao_dados_job.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MigracaoDadosJobConfig {

    private final JobRepository jobRepository;

    public MigracaoDadosJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job migracaoDadosJob(@Qualifier("migracaoPessoasStep") Step migracaoPessoasStep,
                                @Qualifier("migracaoDadosBancariosStep") Step migracaoDadosBancariosStep) {
        return new JobBuilder("migracaoDadosJob", jobRepository).start(migracaoPessoasStep)
                                                                .next(migracaoDadosBancariosStep)
                                                                .incrementer(new RunIdIncrementer())
                                                                .build();
    }
}
