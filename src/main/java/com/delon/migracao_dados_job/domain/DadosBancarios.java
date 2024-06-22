package com.delon.migracao_dados_job.domain;

import lombok.Builder;

@Builder
public record DadosBancarios(Integer id,
                             Integer pessoaId,
                             Integer agencia,
                             Integer conta,
                             Integer banco) {}
