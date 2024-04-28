package br.com.cairu.fesfsus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cairu.fesfsus.models.Licitacao;
import br.com.cairu.fesfsus.repositories.LicitacaoRepository;

@Service
public class LicitacaoService {

    @Autowired
    private LicitacaoRepository licitacaoRepository;

    public ResponseEntity<Object> salvar(Licitacao licitacao) {
        licitacaoRepository.save(licitacao);

        return ResponseEntity.status(201).body("Licitação cadastrada com sucesso!");
    }

    public ResponseEntity<Object> listarLicitacao() {
        List<Licitacao> licitacaos = licitacaoRepository.findAll();
        return ResponseEntity.status(200).body(licitacaos);
    }


    public ResponseEntity<Object> deleteById(Long id) {
        licitacaoRepository.deleteById(id);

        return ResponseEntity.status(201).body("Licitação deletada com sucesso!");
    }
}
