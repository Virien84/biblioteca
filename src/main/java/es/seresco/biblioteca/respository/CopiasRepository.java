package es.seresco.biblioteca.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.biblioteca.model.Copia;

@Repository
public interface CopiasRepository extends JpaRepository<Copia,Long>{

}
