package br.com.cairu.fesfsus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import br.com.cairu.fesfsus.models.Licitacao;
import br.com.cairu.fesfsus.services.LicitacaoService;

@RestController
@RequestMapping(value = "/licitacao")
public class LicitacaoController {

    @Autowired
    private LicitacaoService licitacaoService;

    @PostMapping("/salvar")
    public ResponseEntity<Object> salvar(@RequestBody Licitacao licitacao) {
        return licitacaoService.salvar(licitacao);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
    return licitacaoService.deleteById(id);
}

    @GetMapping("/listar")
    public ResponseEntity<Object> listarLicitacao() {
        return licitacaoService.listarLicitacao();
    }


}
