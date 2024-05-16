package br.com.cairu.fesfsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.fesfsus.dto.UsuarioResponseDTO;
import br.com.cairu.fesfsus.models.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByNome(String nome);

    boolean existsByEmail(String email);

    List<UsuarioResponseDTO> findByEmailLike(String email);

    List<UsuarioResponseDTO> findByNomeLike(String nome);

    List<UsuarioResponseDTO> findAllByOrderByIdDesc();

}