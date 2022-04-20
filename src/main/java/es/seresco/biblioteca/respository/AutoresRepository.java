package es.seresco.biblioteca.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.biblioteca.model.Autor;

@Repository
public interface AutoresRepository extends JpaRepository<Autor,Long>{

}
