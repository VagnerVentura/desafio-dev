package spring.boot.desafio.Cnab.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.desafio.Cnab.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
