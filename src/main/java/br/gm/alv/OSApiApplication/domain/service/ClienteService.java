package br.gm.alv.OSApiApplication.domain.service;

import br.gm.alv.OSApiApplication.domain.exception.DomainException;
import br.gm.alv.OSApiApplication.domain.model.Cliente;
import br.gm.alv.OSApiApplication.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());

        // ID vazio --> Novo Registro
        // ID preenchido --> Alterar existente
        if (clienteExistente != null && !clienteExistente.equals(cliente)) {
            throw new DomainException("Já existe um cliente cadastrado com esse email!");
        }

        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}