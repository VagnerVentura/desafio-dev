package spring.boot.desafio.Cnab.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import spring.boot.desafio.Cnab.Transacao;
import spring.boot.desafio.Cnab.Enums.TipoTransacao;
import spring.boot.desafio.Cnab.Repositories.LojaRepository;
import spring.boot.desafio.Cnab.Repositories.TransacaoRepository;
import spring.boot.desafio.Cnab.models.Loja;

@Service
@AllArgsConstructor
public class TransacaoService {

	private final TransacaoRepository transacaoRepository;
	private LojaRepository lojaRepository;
	
	public void arquivoProcessar (MultipartFile file) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
			String linha;
			while ((linha = br.readLine()) != null) {
				Transacao transacao = parseLinha(linha);
				processarTransacao(transacao);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Transacao> findAll() {
		return transacaoRepository.findAll();
	}


	private Transacao parseLinha(String linha) {
		
		Transacao transacao = new Transacao();
		
		int tipoCodigo = Integer.parseInt(linha.substring(0,1));
		TipoTransacao tipo = TipoTransacao.fromCodigo(tipoCodigo);
		transacao.setTipo(tipo.getDescricao());
		transacao.setNatureza(tipo.getNatureza());
		transacao.setSinal(tipo.getSinal());		
		transacao.setData(LocalDate.parse(linha.substring(1,9), DateTimeFormatter.BASIC_ISO_DATE));
		transacao.setValor(new BigDecimal(linha.substring(9,19)).divide(new BigDecimal(100)));
		transacao.setCpf(linha.substring(19,30));
		transacao.setCartao(linha.substring(30,42));
		transacao.setHora(LocalTime.parse(linha.substring(42,48),DateTimeFormatter.ofPattern("HHmmss")));
		
		String donoLoja = linha.substring(48,62);
		String nomeLoja =  linha.substring(62).trim();
		
		Loja loja = new Loja();
		loja.setDono(donoLoja);
		loja.setNome(nomeLoja);		
		transacao.setLoja(loja);
		
		return transacao;		
	}
	
	private void processarTransacao(Transacao transacao) {
			Loja lojaTemp = transacao.getLoja();
			
			Optional<Loja> lojaExistente = lojaRepository.findByNomeAndDono(
					lojaTemp.getNome(), lojaTemp.getDono());
			
			Loja loja = lojaExistente.orElseGet(()-> {
				Loja novaLoja = new Loja();
				novaLoja.setNome(lojaTemp.getNome());
				novaLoja.setDono(lojaTemp.getDono());
				novaLoja.setSaldo(BigDecimal.ZERO);
				return lojaRepository.save(novaLoja);
			});
			
			BigDecimal valor = transacao.getValor();
			
			if("Saída".equals(transacao.getNatureza())) {
				loja.setSaldo(loja.getSaldo().subtract(valor));
			} else {
				loja.setSaldo(loja.getSaldo().add(valor));
			}
		
			transacao.setLoja(loja);
			lojaRepository.save(loja);
			transacaoRepository.save(transacao);			
	}


	
}
