package br.com.cairu.fesfsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.fesfsus.dto.UsuarioResponseDTO;
import br.com.cairu.fesfsus.models.Usuario;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByNome(String nome);

    boolean existsByEmail(String email);

    List<UsuarioResponseDTO> findByEmailLike(String email);

    List<UsuarioResponseDTO> findByNomeLike(String nome);

}