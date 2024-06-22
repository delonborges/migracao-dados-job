package com.delon.migracao_dados_job.domain;

import lombok.Builder;

import java.util.Date;

@Builder
public record Pessoa(Integer id,
                     String nome,
                     String email,
                     Date dataNascimento,
                     Integer idade) {}
