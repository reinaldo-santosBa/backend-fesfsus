package br.com.cairu.fesfsus.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import br.com.cairu.fesfsus.models.Usuario;
import br.com.cairu.fesfsus.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<Object> salvar(Usuario usuario) {
        usuarioRepository.save(usuario);

        return ResponseEntity.status(201).body("Usuario cadastrado com sucesso!");
    }

    public ResponseEntity<Object> deleteById(Long id) {
        usuarioRepository.deleteById(id);

        return ResponseEntity.status(200).body("Usuario deletado com sucesso!");
    }

    public ResponseEntity<Object> listarUsuario() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.status(200).body(usuarios);
    }

    public ResponseEntity<Object> listarPorEmail(String email) {
        List<Usuario> usuario = usuarioRepository.findByEmail(email);
        return ResponseEntity.status(200).body(usuario);
    }

    public ResponseEntity<Object> listarPorNome(String nome) {
        List<Usuario> usuario = usuarioRepository.findByNome(nome);
        return ResponseEntity.status(200).body(usuario);
    }

}
