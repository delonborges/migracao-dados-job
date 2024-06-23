package com.delon.migracao_dados_job.domain;

import lombok.Builder;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Builder
public record Pessoa(String nome,
                     String email,
                     Date dataNascimento,
                     Integer idade,
                     Integer id) {

    public Boolean isValida() {
        return !Strings.isBlank(nome) && !Strings.isBlank(email) && dataNascimento != null && idade != null;
    }
}
