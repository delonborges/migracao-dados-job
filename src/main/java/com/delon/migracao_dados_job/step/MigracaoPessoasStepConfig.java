package com.delon.migracao_dados_job.step;

import com.delon.migracao_dados_job.domain.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigracaoPessoasStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public MigracaoPessoasStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step migracaoPessoasStep(ItemReader<Pessoa> arquivoPessoaReader,
                                    ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter,
                                    FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {
        return new StepBuilder("migracaoPessoasStep", jobRepository).<Pessoa, Pessoa>chunk(10000, transactionManager)
                                                                    .reader(arquivoPessoaReader)
                                                                    .writer(pessoaClassifierWriter)
                                                                    .stream(arquivoPessoasInvalidasWriter)
                                                                    .build();
    }
}
