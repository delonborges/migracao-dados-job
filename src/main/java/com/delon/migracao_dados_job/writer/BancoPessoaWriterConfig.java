package com.delon.migracao_dados_job.writer;

import com.delon.migracao_dados_job.domain.Pessoa;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;

@Configuration
public class BancoPessoaWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Pessoa> bancoPessoaWriter(@Qualifier("appDataSource") DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<Pessoa>().dataSource(dataSource)
                                                       .sql("INSERT INTO pessoa (id, nome, email, data_nascimento, idade) VALUES (?, ?, ?, ?, ?)")
                                                       .itemPreparedStatementSetter(itemPreparedStatementSetter())
                                                       .build();
    }

    private ItemPreparedStatementSetter<Pessoa> itemPreparedStatementSetter() {

        return (pessoa, ps) -> {
            ps.setInt(1, pessoa.id());
            ps.setString(2, pessoa.nome());
            ps.setString(3, pessoa.email());
            ps.setDate(4, new Date(pessoa.dataNascimento().getTime()));
            ps.setInt(5, pessoa.idade());
        };
    }
}
