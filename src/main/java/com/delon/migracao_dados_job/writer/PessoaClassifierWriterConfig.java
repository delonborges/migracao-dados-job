package com.delon.migracao_dados_job.writer;

import com.delon.migracao_dados_job.domain.Pessoa;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter(JdbcBatchItemWriter<Pessoa> bancoPessoaWriter,
                                                                        FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {

        return new ClassifierCompositeItemWriterBuilder<Pessoa>().classifier(classifier(bancoPessoaWriter, arquivoPessoasInvalidasWriter)).build();
    }

    private Classifier<Pessoa, ItemWriter<? super Pessoa>> classifier(JdbcBatchItemWriter<Pessoa> bancoPessoaWriter,
                                                                      FlatFileItemWriter<Pessoa> arquivoPessoasInvalidasWriter) {

        return pessoa -> Boolean.TRUE.equals(pessoa.isValida()) ? bancoPessoaWriter : arquivoPessoasInvalidasWriter;
    }
}
