
package spring.boot.desafio.Cnab.dto;

import java.math.BigDecimal;
import java.util.List;

import spring.boot.desafio.Cnab.Transacao;

public class LojaResumoDto {

	private String nomeLoja;
	private String donoLoja;
	private List <Transacao> transacoes;
	private BigDecimal saldo;	
	
}
