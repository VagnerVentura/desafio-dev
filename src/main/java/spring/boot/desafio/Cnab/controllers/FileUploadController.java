package spring.boot.desafio.Cnab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.boot.desafio.Cnab.Transacao;
import spring.boot.desafio.Cnab.services.TransacaoService;

@RestController
@RequestMapping("/upload")
public class FileUploadController {

	@Autowired
	TransacaoService transacaoService;
	
	@PostMapping
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		transacaoService.arquivoProcessar(file);
		return ResponseEntity.ok("Arquivo Processado!"); 
	}
	
	@GetMapping
	public ResponseEntity<List<Transacao>> listarTransacao(){
		return  ResponseEntity.ok(transacaoService.findAll());
	}
			
	
}
