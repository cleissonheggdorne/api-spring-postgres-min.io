package br.gov.mt.seplag.seletivo.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UnidadeRecordDTO(
    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    String nome,
    @NotBlank(message = "Sigla não pode ser nula ou vazia")
    String sigla
) {}
