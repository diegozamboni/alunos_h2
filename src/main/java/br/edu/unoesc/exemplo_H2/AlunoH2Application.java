package br.edu.unoesc.exemplo_H2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.edu.unoesc.exemplo_H2.model.Aluno;
import br.edu.unoesc.exemplo_H2.repository.AlunoRepository;

@SpringBootApplication
public class AlunoH2Application {

	public static void main(String[] args) {
		SpringApplication.run(AlunoH2Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AlunoRepository repositorio) {
		return args -> {
			Aluno l = new Aluno(null, "Maria", new BigDecimal(2000), LocalDate.of(1991, 05, 11));
			l = repositorio.save(l);
			System.out.println(l);
			
			repositorio.save(new Aluno(null, "Pedro", new BigDecimal(2000), LocalDate.of(1991, 05, 11)));
			
			Optional<Aluno> p = repositorio.findById(2);
			if (p.isEmpty()) {
				System.out.println("Registro não encontrado!");
			} else {
				System.out.println(p.get());				
			}
			
			Aluno antigo = repositorio.findById(3).get();
			antigo.setNome("Macelo");
			antigo.setSalario(new BigDecimal(3500));
			antigo.setNascimento(LocalDate.of(1991, 05, 11));
			repositorio.save(antigo);
			
			System.out.println(repositorio.findAll());
			
			for (var aluno: repositorio.findByNomeContainingIgnoreCase("m")) {
				System.out.println(aluno);
			}
			
			for (var aluno: repositorio.porSalario(new BigDecimal(1500))) {
				System.out.println(aluno);
			}
			
			for (var aluno: repositorio.findByFiltroNormal("Fernando")) {
				System.out.println(aluno);
			}
		};
	}
	
}