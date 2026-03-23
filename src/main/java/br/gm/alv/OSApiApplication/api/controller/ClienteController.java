package br.gm.alv.OSApiApplication.api.controller;

import br.gm.alv.OSApiApplication.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author digma
 */
@RestController
public class ClienteController {

    List<Cliente> listaClientes;

    @GetMapping("/clientes")
    public List<Cliente> listas() {

        listaClientes = new ArrayList<Cliente>();
        listaClientes.add(new Cliente(1, "Alvaro", "alvaro@teste.com", "11-99999-9999"));
        listaClientes.add(new Cliente(1, "Joao", "joao@teste.com", "11-88888-9999"));
        listaClientes.add(new Cliente(1, "Maria", "maria@teste.com", "11-77777-9999"));

        return listaClientes;
    }
}
