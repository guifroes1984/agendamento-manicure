package com.guifroes1984.agendamento.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guifroes1984.agendamento.enuns.Role;
import com.guifroes1984.agendamento.model.AuthRegisterRequest;
import com.guifroes1984.agendamento.model.Usuario;
import com.guifroes1984.agendamento.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;

	public Usuario registrar(AuthRegisterRequest req) {

		if (usuarioRepository.findByEmail(req.getEmail()).isPresent()) {
			throw new RuntimeException("E-mail já está em uso!");
		}

		Usuario usuario = Usuario.builder().nome(req.getNome()).email(req.getEmail())
				.senha(passwordEncoder.encode(req.getSenha()))
				.role(req.getRole() != null ? req.getRole() : Role.CLIENTE).build();

		return usuarioRepository.save(usuario);
	}

}
