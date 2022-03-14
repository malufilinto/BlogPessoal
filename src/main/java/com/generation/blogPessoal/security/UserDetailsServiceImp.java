package com.generation.blogPessoal.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	private @Autowired UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> optional = repository.findByUsuario(username);

		if (optional.isPresent()) {
			return new UserDetailsImp(optional.get());
		} else {
			throw new UsernameNotFoundException("Usuario não existe");
		}
	}
}
