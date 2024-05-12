package br.com.cairu.fesfsus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cairu.fesfsus.models.Legislacao;

public interface LegislacaoRepository extends JpaRepository<Legislacao, Long> {

    boolean existsByNumeroLegislacao(String numeroLegislacao);

    boolean existsByTitulo(String titulo);

}
