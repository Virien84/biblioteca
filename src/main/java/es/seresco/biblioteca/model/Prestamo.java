package es.seresco.biblioteca.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "PRESTAMO", uniqueConstraints = {
		@UniqueConstraint(name = "PRESTAMO_UK_0", columnNames = { "ID_COPIA", "ID_USUARIO", "FECHA_INICIO" }) })
public class Prestamo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8701897683893933707L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_INICIO", nullable = false)
	private Date fechaInicio;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_FIN", nullable = false)
	private Date fechaFin;

	@ManyToOne
	@JoinColumn(name = "ID_COPIA")
	private Copia copia;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

}
