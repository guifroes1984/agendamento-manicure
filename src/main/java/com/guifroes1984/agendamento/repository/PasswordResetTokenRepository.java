package com.guifroes1984.agendamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guifroes1984.agendamento.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	Optional<PasswordResetToken> findByToken(String token);
}
