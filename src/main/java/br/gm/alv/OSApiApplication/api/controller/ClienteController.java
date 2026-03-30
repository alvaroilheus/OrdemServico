package br.gm.alv.OSApiApplication.api.controller;

import br.gm.alv.OSApiApplication.domain.model.Cliente;
import br.gm.alv.OSApiApplication.domain.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // GET - listar todos
    @GetMapping("/clientes/all")
    public List<Cliente> listas() {
        return clienteRepository.findAll();
    }

    // GET - buscar por ID
    @GetMapping("/clientes/id/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET - buscar por nome
    @GetMapping("/clientes/nome/{nome}")
    public List<Cliente> findByNome(@PathVariable String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }

    // POST - adicionar
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // PUT - atualizar
    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteID,
                                              @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteID);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    // DELETE - excluir
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(clienteID);
        return ResponseEntity.noContent().build();
    }
}