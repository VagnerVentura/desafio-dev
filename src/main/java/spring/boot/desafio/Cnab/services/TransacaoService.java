package spring.boot.desafio.Cnab.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import spring.boot.desafio.Cnab.Transacao;
import spring.boot.desafio.Cnab.Enums.TipoTransacao;
import spring.boot.desafio.Cnab.Repositories.TransacaoRepository;

@Service
@AllArgsConstructor
public class TransacaoService {

	TransacaoRepository transacaoRepository;
	
	public void arquivoProcessar (MultipartFile file) {
		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))){
			String linha;
			while ((linha = br.readLine()) != null) {
				Transacao transacao = parseLinha(linha);
				transacaoRepository.save(transacao);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
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
		transacao.setDonoLoja(linha.substring(48,62));
		transacao.setNomeLoja(linha.substring(62).trim());
		return transacao;
		
	}

	public List<Transacao> findAll() {
		return transacaoRepository.findAll();
	}
	
	
}
