package br.usjt.eng2020_1_a12.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.usjt.eng2020_1_a12.model.beans.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Long>{

	@Query(value = "SELECT * FROM tb_cidade WHERE nome LIKE CONCAT ('%',:letra,'%')", nativeQuery = true)
	public List<Cidade> findByInicial(@Param("letra") String letra);
	
	@Query(value = "SELECT * FROM tb_cidade WHERE latitude = :latitude AND longitude = :longitude", nativeQuery = true)
	public Cidade findByLatAndLong(@Param("latitude")double latitude, @Param("longitude")double longitude);
	
}