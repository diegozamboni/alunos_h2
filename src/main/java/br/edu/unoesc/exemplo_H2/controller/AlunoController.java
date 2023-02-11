package br.edu.unoesc.exemplo_H2.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.exemplo_H2.model.Aluno;
import br.edu.unoesc.exemplo_H2.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository repositorio;
	
	@GetMapping("/find")
	List<Aluno> listarComFiltro(@RequestParam("filtro") String filtro) {
		return repositorio.findByNomeContainingIgnoreCase(filtro);
	}
	
	@GetMapping
	public Iterable<Aluno> listarTudo() {
		return repositorio.findAll();
	}
	
	@GetMapping("/salario")
	List<Aluno> listarPorSalario(@RequestParam("salario") BigDecimal salario) {
		return repositorio.porSalario(salario);
	}
	
	@PostMapping
	public Aluno salvarAluno(@RequestBody Aluno aluno) {
		repositorio.save(aluno);
		
		System.out.println("Inserindo aluno...");
		System.out.println(aluno);
		
		return aluno;
	}
	
	@PutMapping
	public Aluno atualizarAluno(@RequestBody Aluno aluno) {
		Aluno a = repositorio.porId(aluno.getId());
		a.setNome(aluno.getNome());
		a.setSalario(aluno.getSalario());
		a.setNascimento(aluno.getNascimento());
		
		repositorio.save(a);
		
		System.out.println("Atualizando o aluno...");
		System.out.println(a);
		
		return a;
	}
	
	@DeleteMapping(value = "/{id}")
	public void atualizarAluno(@PathVariable Integer id) {
		Aluno a = repositorio.porId(id);
		
		repositorio.delete(a);
		
		System.out.println("Excluindo o aluno [" + id + "]...");
	}
	
}