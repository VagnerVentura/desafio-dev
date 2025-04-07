package spring.boot.desafio.Cnab;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "transacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tipo; 
	private LocalDate data;
	private BigDecimal valor; 
	private String cpf;
	private String natureza;
	private String sinal;
	private String cartao;
	private LocalTime hora;
	private String donoLoja;
	private String nomeLoja;	
	
	
	
}
