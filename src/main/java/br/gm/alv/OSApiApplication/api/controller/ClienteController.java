package br.gm.alv.OSApiApplication.api.controller;

import br.gm.alv.OSApiApplication.domain.model.Cliente;
import jakarta.persistence.EntityManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author digma
 */
@RestController
public class ClienteController {
    
    @PersistenceContext
    private EntityManager manager;        
    
    @GetMapping("/clientes")
    public List<Cliente> listas(){
        
        return manager.createQuery("from Cliente", Cliente.class)
                .getResultList();
    }
}
