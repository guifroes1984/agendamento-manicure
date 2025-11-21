package com.guifroes1984.agendamento.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TBL_MANICURES")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Manicure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	private String bio;
	private String especialidades;
	private String fotoUrl;
	
	@Column(length = 2000)
	private String portfolioUrls;

	private Double notaMedia;
	
	@Column(length = 2000)
	private String configuracaoHorarioJson;
	
}
