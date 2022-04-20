package es.seresco.biblioteca.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.biblioteca.model.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario,Long>{

}
