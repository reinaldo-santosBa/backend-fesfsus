package br.com.cairu.fesfsus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import br.com.cairu.fesfsus.models.Legislacao;
import br.com.cairu.fesfsus.repositories.LegislacaoRepository;
import br.com.cairu.fesfsus.services.LegislacaoService;

@RestController
@RequestMapping(value = "/legislacao")
public class LegislacaoController {

    @Autowired
    private LegislacaoService legislacaoService;

    @Autowired
    private LegislacaoRepository legislacaoRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvar(@RequestPart("data") Legislacao legislacao,
            @RequestPart("detalhe") String detalhe) {
        String titulo = legislacao.getTitulo();
        String numeroLegislacao = legislacao.getNumeroLegislacao();
        legislacao.setDetalhe(detalhe);

        if (legislacaoRepository.existsByNumeroLegislacao(numeroLegislacao)) {
            return ResponseEntity.badRequest().body("Número da legislação já existe!");
        }

        if (legislacaoRepository.existsByTitulo(titulo)) {
            return ResponseEntity.badRequest().body("Título da legislação já existe!");
        }

        return legislacaoService.salvar(legislacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        return legislacaoService.deleteById(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listarLegislacao() {
        return legislacaoService.listarLegislacao();
    }

    @GetMapping(value = "/{id}")
    public Legislacao findById(@PathVariable Long id) {
        Legislacao legislacao = legislacaoRepository.findById(id).get();
        return legislacao;

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Legislacao legislacaoDetalhes) {

        if (legislacaoRepository.existsById(id)) {

            Legislacao legislacao = legislacaoRepository.findById(id).get();

            legislacao.setTitulo(legislacaoDetalhes.getTitulo());
            legislacao.setNumeroLegislacao(legislacaoDetalhes.getNumeroLegislacao());
            legislacao.setDetalhe(legislacaoDetalhes.getDetalhe());

            legislacaoRepository.save(legislacao);

            return ResponseEntity.ok("Legislacao atualizado com sucesso!");
        }

        else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Legislacao não encontrada");
        }
    }

}
