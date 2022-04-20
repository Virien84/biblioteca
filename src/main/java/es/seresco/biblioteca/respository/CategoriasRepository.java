package es.seresco.biblioteca.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.biblioteca.model.Categoria;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria,Long>{

}
