package br.com.cairu.fesfsus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.cairu.fesfsus.models.Usuario;
import br.com.cairu.fesfsus.repositories.UsuarioRepository;
import br.com.cairu.fesfsus.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // SALVAR
    @PostMapping("/salvar")
    public ResponseEntity<Object> salvar(@RequestBody Usuario usuario) {

        if (usuarioRepository.existsByNome(usuario.getNome())) {
            return ResponseEntity.badRequest().body("Nome de usuario já existe!");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Email de usuario ja existe!");
        } else {

            return usuarioService.salvar(usuario);
        }
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        return usuarioService.deleteById(id);
    }

    // PESQUISAR TODOS
    @GetMapping("/listar")
    public ResponseEntity<Object> listarUsuario() {
        return usuarioService.listarUsuario();
    }

    // PESQUISAR POR ID
    @GetMapping(value = "/{id}")
    public Usuario findById(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuario;

    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Usuario usuarioDetalhes) {

        if (usuarioRepository.existsById(id)) {

            Usuario usuario = usuarioRepository.findById(id).get();

            usuario.setNome(usuarioDetalhes.getNome());
            usuario.setEmail(usuarioDetalhes.getEmail());
            usuario.setSenha(usuarioDetalhes.getSenha());

            usuarioRepository.save(usuario);

            return ResponseEntity.ok("Usuario atualizado com sucesso!");
        }

        else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrada");
        }
    }

}
