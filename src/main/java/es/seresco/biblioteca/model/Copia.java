package es.seresco.biblioteca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.seresco.biblioteca.model.tipos.TipoEstado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "COPIA")
public class Copia implements Serializable {

	private static final long serialVersionUID = -4347117274148071411L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CODIGO", nullable = false, length = 10, unique = true)
	private String codigo;

	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false, length = 15)
	private TipoEstado estado;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "ID_LIBRO")
	private Libro libro;
}
