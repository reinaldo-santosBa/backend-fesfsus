package br.com.cairu.fesfsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.fesfsus.models.Licitacao;

public interface LicitacaoRepository extends JpaRepository<Licitacao, Long> {
    boolean existsByNumeroLicitacao(String numeroLicitacao);

    boolean existsByTitulo(String titulo);

}
