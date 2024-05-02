package br.com.cairu.fesfsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.fesfsus.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    boolean existsByNome(String nome);

    boolean existsByEmail(String email);

}