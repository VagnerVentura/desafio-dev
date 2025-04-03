package spring.boot.desafio.Cnab.Enums;

public enum TipoTransacao {
	
	DEBITO(1, "Débito", "Entrada", "+"),
	BOLETO(2, "Boleto", "Saída", "-"),	
	FINANCIAMENTO(3, "Financiamento", "Saída", "-"),
    CREDITO(4, "Crédito", "Entrada", "+"),
    RECEBIMENTO_EMPRESTIMO(5, "Recebimento Empréstimo", "Entrada", "+"),
    VENDAS(6, "Vendas", "Entrada", "+"),
    RECEBIMENTO_TED(7, "Recebimento TED", "Entrada", "+"),
    RECEBIMENTO_DOC(8, "Recebimento DOC", "Entrada", "+"),
    ALUGUEL(9, "Aluguel", "Saída", "-");
	
	private final int codigo;
	private final String descricao;
	private final String natureza;
	private final String sinal;
	
	private TipoTransacao(int codigo, String descricao, String natureza, String sinal){
		this.codigo = codigo;
		this.descricao = descricao;
		this.natureza = natureza;
		this.sinal = sinal;
	}
	
}
