package com.guifroes1984.agendamento.model;

import com.guifroes1984.agendamento.enuns.Role;

import lombok.Data;

@Data
public class AuthRegisterRequest {
	private String nome;
	private String email;
	private String senha;
	private Role role;
}
