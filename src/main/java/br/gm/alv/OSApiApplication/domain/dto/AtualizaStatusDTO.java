package br.gm.alv.OSApiApplication.domain.dto;

import br.gm.alv.OSApiApplication.domain.model.StatusOrdemServico;
import jakarta.validation.constraints.NotNull;

public record AtualizaStatusDTO(

    @NotNull(message = "Status é obrigatório")
    StatusOrdemServico status    
    
){}
