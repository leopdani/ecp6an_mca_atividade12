package br.usjt.eng2020_1_a12.model.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.eng2020_1_a12.model.beans.Cidade;
import br.usjt.eng2020_1_a12.model.repository.CidadeRepository;


@RestController

@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidRepo;
	
	@GetMapping("/lista")
	public List<Cidade> todosOsCidades(){
		return cidRepo.findAll();
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
		Cidade c = cidRepo.save(cidade);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentServletMapping().path("/{id}").
				buildAndExpand(cidade.getId()).
				toUri();
		return ResponseEntity.created(uri).body(c);
	}
	
	@GetMapping("/buscaId/{id}")
	public Cidade buscarPeloId(@PathVariable Long id) {
		return cidRepo.getOne(id);
	}
	
	@GetMapping("/buscaInicial/{letra}")
	public List<Cidade> buscarPelaInicial(@PathVariable String letra) {
		return cidRepo.findByInicial(letra);
	}
	
	@GetMapping("/{latitude}/{longitude}")
	public Cidade buscarPelaLatELong(@PathVariable double latitude, @PathVariable double longitude) {
		return cidRepo.findByLatAndLong(latitude, longitude);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade){
		Cidade c = cidRepo.findById(cidade.getId()).get();
		c.setNome(cidade.getNome());
		c.setLatitude(cidade.getLatitude());
		c.setLongitude(cidade.getLongitude());
		c = cidRepo.save(c);
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}
	
	@DeleteMapping("/{id}")
	public void excluirPeloId(@PathVariable Long id) {
		Cidade c =  cidRepo.getOne(id);
		cidRepo.delete(c);
	}
	
	
	
}
