package spring.boot.desafio.Cnab.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.boot.desafio.Cnab.Transacao;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_lojas", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "dono"}))
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String dono;
	private BigDecimal saldo;
	
	
	@OneToMany(mappedBy = "loja")
	private List<Transacao> transacoes;
	
}
