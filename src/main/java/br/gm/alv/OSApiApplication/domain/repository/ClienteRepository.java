package br.gm.alv.OSApiApplication.domain.repository;

import br.gm.alv.OSApiApplication.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   
    List<Cliente> findByNome(String nome);

    List<Cliente> findByNomeContaining(String nome);

}