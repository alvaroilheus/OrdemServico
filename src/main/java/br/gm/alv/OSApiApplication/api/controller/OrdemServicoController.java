package br.gm.alv.OSApiApplication.api.controller;

import br.gm.alv.OSApiApplication.domain.model.OrdemServico;
import br.gm.alv.OSApiApplication.domain.repository.OrdemServicoRepository;
import br.gm.alv.OSApiApplication.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    // 1. Criar Ordem de Serviço (Passo 9 do PDF)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }

    // 2. Listar todas as OS (Requisito pág. 8)
    @GetMapping
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    // 3. Buscar OS por ID (Requisito pág. 8)
    @GetMapping("/{osId}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long osId) {
        Optional<OrdemServico> os = ordemServicoRepository.findById(osId);
        
        if (os.isPresent()) {
            return ResponseEntity.ok(os.get());
        }
        return ResponseEntity.notFound().build();
    }

    // 4. Excluir uma OS (Requisito pág. 8)
    @DeleteMapping("/{osId}")
    public ResponseEntity<Void> excluir(@PathVariable Long osId) {
        if (!ordemServicoRepository.existsById(osId)) {
            return ResponseEntity.notFound().build();
        }
        ordemServicoRepository.deleteById(osId);
        return ResponseEntity.noContent().build();
    }

    // 5. DESAFIO KGE: Listar OS por código de um cliente (Pág. 8)
    @GetMapping("/cliente/{clienteId}")
    public List<OrdemServico> listarPorCliente(@PathVariable Long clienteId) {
        return ordemServicoRepository.findByClienteId(clienteId);
    }
    
}