package br.gov.mt.seplag.seletivo.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record ServidorTemporarioRecordDTO(
    Integer id,
    @NotBlank(message = "Pessoa não pode ser nula ou vazia")
    Integer idPessoa,
    @NotBlank(message = "Data Admissão não pode ser nula ou vazia")
    LocalDate dataAdmissao,
    LocalDate dataDemissao
) {}
