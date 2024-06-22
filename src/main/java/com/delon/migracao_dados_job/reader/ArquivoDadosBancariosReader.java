package com.delon.migracao_dados_job.reader;

import com.delon.migracao_dados_job.domain.DadosBancarios;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoDadosBancariosReader {

    @Bean
    public FlatFileItemReader<DadosBancarios> arquivoDadosBancariosReader() {
        return new FlatFileItemReaderBuilder<DadosBancarios>().name("arquivoDadosBancariosReader")
                                                              .resource(new FileSystemResource("files/dados_bancarios.csv"))
                                                              .delimited()
                                                              .names("pessoaId", "agencia", "conta", "banco", "id")
                                                              .addComment("--")
                                                              .targetType(DadosBancarios.class)
                                                              .build();
    }
}
