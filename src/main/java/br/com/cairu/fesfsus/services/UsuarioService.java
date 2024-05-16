package br.com.cairu.fesfsus.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cairu.fesfsus.config.TokenService;
import br.com.cairu.fesfsus.dto.LoginRequestDTO;
import br.com.cairu.fesfsus.dto.LoginResponseDTO;
import br.com.cairu.fesfsus.dto.UsuarioResponseDTO;
import br.com.cairu.fesfsus.models.Usuario;
import br.com.cairu.fesfsus.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> salvar(Usuario usuario) {
        usuarioRepository.save(usuario);

        return ResponseEntity.status(201).body("Usuario cadastrado com sucesso!");
    }

    public ResponseEntity<Object> deleteById(Long id) {
        usuarioRepository.deleteById(id);

        return ResponseEntity.status(200).body("Usuario deletado com sucesso!");
    }

    public ResponseEntity<Object> listarUsuario() {
        List<UsuarioResponseDTO> usuarios = usuarioRepository.findAllByOrderByIdDesc();
        return ResponseEntity.status(200).body(usuarios);
    }

    public ResponseEntity<Object> listarPorEmail(Usuario usuario) {
        List<UsuarioResponseDTO> usuarios = usuarioRepository.findByEmailLike("%" + usuario.getEmail() + "%");
        return ResponseEntity.status(200).body(usuarios);
    }

    public ResponseEntity<Object> listarPorNome(Usuario usuario) {
        List<UsuarioResponseDTO> usuarios = usuarioRepository.findByNomeLike("%" + usuario.getNome() + "%");
        return ResponseEntity.status(200).body(usuarios);
    }

    public ResponseEntity<Object> login(LoginRequestDTO userDTO) {
        try {
            System.out.println(userDTO.email());
            Usuario user = usuarioRepository.findByEmail(userDTO.email())
                    .orElseThrow(() -> new UsernameNotFoundException("email ou senha inválidos!"));

            if (passwordEncoder.matches(userDTO.senha(), user.getPassword())) {
                String token = this.tokenService.generateToken(user);

                return ResponseEntity.ok().body(new LoginResponseDTO(user.getNome(), user.getEmail(), token));
            }

            return ResponseEntity.status(400).body("email ou senha inválidos!");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
