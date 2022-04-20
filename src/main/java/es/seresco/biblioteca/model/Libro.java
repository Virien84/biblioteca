package es.seresco.biblioteca.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "LIBRO")
public class Libro implements Serializable {

	private static final long serialVersionUID = -4347117274148071411L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ISBN", nullable = false, length = 100, unique = true)
	private String isbn;

	@Column(name = "TITULO", nullable = false, length = 50)
	private String titulo;

	@Column(name = "EDITORIAL", nullable = false, length = 50)
	private String editorial;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;

	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "ID_AUTOR")
	private Autor autor;

}
