package com.guifroes1984.agendamento.service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiracao-ms}")
	private Long jwtExpirationMs;

	private Key signingKey;

	@PostConstruct
	public void init() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.signingKey = Keys.hmacShaKeyFor(keyBytes);
	}

	public String gerarToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + jwtExpirationMs);
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(now)
				.setExpiration(expiry).signWith(signingKey, SignatureAlgorithm.HS256).compact();
	}

	public String gerarToken(UserDetails userDetails) {
		return gerarToken(Map.of(), userDetails);
	}

	public String extrairUsername(String token) {
		return extrairClaim(token, Claims::getSubject);
	}

	public Date extrairExpiracao(String token) {
		return extrairClaim(token, Claims::getExpiration);
	}

	public <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

	public boolean isTokenValido(String token, UserDetails userDetails) {
		final String username = extrairUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpirado(token));
	}

	private boolean isTokenExpirado(String token) {
		return extrairExpiracao(token).before(new Date());
	}

}
