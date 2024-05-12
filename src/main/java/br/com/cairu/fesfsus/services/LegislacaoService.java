package br.com.cairu.fesfsus.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cairu.fesfsus.models.Legislacao;
import br.com.cairu.fesfsus.repositories.LegislacaoRepository;

@Service
public class LegislacaoService {

    @Autowired
    private LegislacaoRepository legislacaoRepository;

    public ResponseEntity<Object> salvar(Legislacao legislacao) {

        legislacaoRepository.save(legislacao);

        return ResponseEntity.status(201).body("Legislação cadastrada com sucesso!");
    }

    public ResponseEntity<Object> listarLegislacao() {
        List<Legislacao> legislacoes = legislacaoRepository.findAll();
        return ResponseEntity.status(200).body(legislacoes);
    }

    public ResponseEntity<Object> deleteById(Long id) {
        legislacaoRepository.deleteById(id);

        return ResponseEntity.status(201).body("Legislação deletada com sucesso!");
    }
}
