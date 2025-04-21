package spring.boot.desafio.Cnab.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.boot.desafio.Cnab.models.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long>{

	Optional<Loja> findByNomeAndDono(String nome, String dono);

}
