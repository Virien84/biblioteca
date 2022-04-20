package es.seresco.biblioteca.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.biblioteca.model.Libro;

@Repository
public interface LibrosRepository extends JpaRepository<Libro,Long>{

}
