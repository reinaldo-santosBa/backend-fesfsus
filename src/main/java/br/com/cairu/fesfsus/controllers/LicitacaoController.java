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
import br.com.cairu.fesfsus.models.Licitacao;
import br.com.cairu.fesfsus.repositories.LicitacaoRepository;
import br.com.cairu.fesfsus.services.LicitacaoService;

@RestController
@RequestMapping(value = "/licitacao")
public class LicitacaoController {

    @Autowired
    private LicitacaoService licitacaoService;

    @Autowired
    private LicitacaoRepository licitacaoRepository;

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvar(@RequestBody Licitacao licitacao) {

        if (licitacaoRepository.existsByNumeroLicitacao(licitacao.getNumeroLicitacao())) {
            return ResponseEntity.badRequest().body("Número da licitação já existe!");
        }

        if (licitacaoRepository.existsByTitulo(licitacao.getTitulo())) {
            return ResponseEntity.badRequest().body("Titulo da licitação ja existe!");
        } else {

            return licitacaoService.salvar(licitacao);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        return licitacaoService.deleteById(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<Object> listarLicitacao() {
        return licitacaoService.listarLicitacao();
    }

    @GetMapping(value = "/{id}")
    public Licitacao findById(@PathVariable Long id) {
        Licitacao licitacao = licitacaoRepository.findById(id).get();
        return licitacao;

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Licitacao licitacaoDetalhes) {

        if (licitacaoRepository.existsById(id)) {

            Licitacao licitacao = licitacaoRepository.findById(id).get();

            licitacao.setTitulo(licitacaoDetalhes.getTitulo());
            licitacao.setNumeroLicitacao(licitacaoDetalhes.getNumeroLicitacao());
            licitacao.setDescricao(licitacaoDetalhes.getDescricao());
            licitacao.setDetalhe(licitacaoDetalhes.getDetalhe());

            licitacaoRepository.save(licitacao);

            return ResponseEntity.ok("Licitação atualizado com sucesso!");
        }

        else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Licitação não encontrada");
        }
    }

}
