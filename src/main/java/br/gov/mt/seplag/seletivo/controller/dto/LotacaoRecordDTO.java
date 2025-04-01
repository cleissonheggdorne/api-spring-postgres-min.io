package br.gov.mt.seplag.seletivo.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public record LotacaoRecordDTO(
    Integer id,
    @NotBlank(message = "Pessoa não pode ser nula ou vazia")
    Integer idPessoa,
    @NotBlank(message = "Unidade não pode ser nula ou vazia")
    Integer idUnidade,
    @NotBlank(message = "Data de Lotacao não pode ser nula ou vazia")
    LocalDate dataLotacao,

    LocalDate dataRemocao,
    @NotBlank(message = "Portaria não pode ser nula ou vazia")
    String portaria
) {}
