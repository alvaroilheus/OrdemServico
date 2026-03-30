package br.gm.alv.OSApiApplication.domain.repository;

import br.gm.alv.OSApiApplication.domain.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
    // Método para o desafio: busca todas as OS de um cliente específico
    List<OrdemServico> findByClienteId(Long clienteId);
}