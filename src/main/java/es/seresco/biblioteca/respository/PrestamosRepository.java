package es.seresco.biblioteca.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.seresco.biblioteca.model.Prestamo;

@Repository
public interface PrestamosRepository extends JpaRepository<Prestamo,Long>{
	
	Prestamo findPrestamoById(Long idPrestamo);
}
