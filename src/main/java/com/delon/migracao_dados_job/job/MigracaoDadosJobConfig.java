package com.delon.migracao_dados_job.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class MigracaoDadosJobConfig {

    private final JobRepository jobRepository;

    public MigracaoDadosJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job migracaoDadosJob(@Qualifier("migracaoPessoasStep") Step migracaoPessoasStep,
                                @Qualifier("migracaoDadosBancariosStep") Step migracaoDadosBancariosStep) {
        return new JobBuilder("migracaoDadosJob", jobRepository).start(stepsParalelos(migracaoPessoasStep, migracaoDadosBancariosStep))
                                                                .end()
                                                                .incrementer(new RunIdIncrementer())
                                                                .build();
    }

    private Flow stepsParalelos(Step migracaoPessoasStep, Step migracaoDadosBancariosStep) {
        Flow migracaoDadosBancariosFlow = new FlowBuilder<Flow>("migracaoDadosBancariosFlow").start(migracaoDadosBancariosStep).build();
        return new FlowBuilder<Flow>("stepsParalelosFlow").start(migracaoPessoasStep)
                                                          .split(new SimpleAsyncTaskExecutor())
                                                          .add(migracaoDadosBancariosFlow)
                                                          .build();
    }
}
