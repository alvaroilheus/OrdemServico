package br.gm.alv.OSApiApplication.api.controller;

import br.gm.alv.OSApiApplication.domain.model.Cliente;
import br.gm.alv.OSApiApplication.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author digma
 */
@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listas() {
        return clienteRepository.findAll();
    }

    // Buscar por ID
    @GetMapping("/clientes/id/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar por nome
    @GetMapping("/clientes/nome/{nome}")
    public List<Cliente> findByNome(@PathVariable String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }
}
