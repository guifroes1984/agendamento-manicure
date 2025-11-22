package com.guifroes1984.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifroes1984.agendamento.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
