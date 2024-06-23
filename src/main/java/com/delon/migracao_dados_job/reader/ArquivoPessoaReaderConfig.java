package com.delon.migracao_dados_job.reader;

import com.delon.migracao_dados_job.domain.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;

@Configuration
public class ArquivoPessoaReaderConfig {

    private static final String DATA_NASCIMENTO = "dataNascimento";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private static final String IDADE = "idade";
    private static final String NOME = "nome";

    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaReader() {
        return new FlatFileItemReaderBuilder<Pessoa>().name("arquivoPessoaReader")
                                                      .resource(new FileSystemResource("src/main/resources/files/pessoas.csv"))
                                                      .delimited()
                                                      .names(NOME, EMAIL, DATA_NASCIMENTO, IDADE, ID)
                                                      .addComment("--")
                                                      .fieldSetMapper(fieldSetMapper())
                                                      .build();
    }

    private FieldSetMapper<Pessoa> fieldSetMapper() {
        return fieldSet -> Pessoa.builder()
                                 .nome(fieldSet.readString(NOME))
                                 .email(fieldSet.readString(EMAIL))
                                 .dataNascimento(optDate(fieldSet))
                                 .idade(optInt(fieldSet, IDADE))
                                 .id(optInt(fieldSet, ID))
                                 .build();
    }

    private Date optDate(FieldSet fieldSet) {
        String dataNascimentoText = fieldSet.readString(DATA_NASCIMENTO);
        if (!dataNascimentoText.isEmpty()) {
            java.util.Date dataNascimento = fieldSet.readDate(DATA_NASCIMENTO, DATE_FORMAT);
            return new Date(dataNascimento.getTime());
        }
        return null;
    }

    private Integer optInt(FieldSet fieldSet, String fieldName) {
        int fieldValue = fieldSet.readInt(fieldName, Integer.MIN_VALUE);
        return fieldValue == Integer.MIN_VALUE ? null : fieldValue;
    }
}
