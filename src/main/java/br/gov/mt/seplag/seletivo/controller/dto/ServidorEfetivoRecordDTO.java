package br.gov.mt.seplag.seletivo.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record ServidorEfetivoRecordDTO(
    Integer id,
    @NotBlank(message = "Pessoa não pode ser nula ou vazia")
    Integer idPessoa,
    @NotBlank(message = "Matrícula não pode ser nula ou vazia")
    String matricula
) {}
