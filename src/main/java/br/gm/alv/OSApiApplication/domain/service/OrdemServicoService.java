package br.gm.alv.OSApiApplication.domain.service;

import br.gm.alv.OSApiApplication.domain.exception.DomainException;
import br.gm.alv.OSApiApplication.domain.model.OrdemServico;
import br.gm.alv.OSApiApplication.domain.model.StatusOrdemServico;
import br.gm.alv.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
    
    /**
     * Implementa a atualização de Status da Ordem de Serviço
     * Verifica se OS existe ou não antes de salvar.
     * * @param ordemServicoID
     * @param status
     * @return Optional<OrdemServico> or throw if not found.
     */

    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status) {

        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);

        if (optOrdemServico.isPresent()) {

            OrdemServico ordemServico = optOrdemServico.get();

            // Verifica se ordem está ABERTA.
            if (ordemServico.getStatus() == StatusOrdemServico.ABERTA 
                && status != StatusOrdemServico.ABERTA) {

                ordemServico.setStatus(status);
                ordemServico.setDataFinalizacao(LocalDateTime.now());
                ordemServicoRepository.save(ordemServico);
                return Optional.of(ordemServico);

            } else {

                // ops.. ordem FINALIZADA ou CANCELADA. Não alterar.
                return Optional.empty();
            }

        } else {
            // Lança exception se ID não encontrado.
            throw new DomainException("Não existe OS com o id " + ordemServicoID);
        }
    }
}


