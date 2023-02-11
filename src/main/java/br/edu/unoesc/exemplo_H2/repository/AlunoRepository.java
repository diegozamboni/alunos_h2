package br.edu.unoesc.exemplo_H2.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.unoesc.exemplo_H2.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {
	
	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
	
	@Query("Select a from Aluno a where a.id = :id")
	public Aluno porId(Integer id);
	
	@Query("Select a from Aluno a where a.salario >= :salario")
	public List<Aluno> porSalario(@Param("salario") BigDecimal salario);
	
	@Query("Select a from Aluno a where upper(a.nome) like upper(concat('%', :filtro, '%')) order by nome")
	public List<Aluno> findByFiltroNormal(@Param("filtro") String filtro);
	
}