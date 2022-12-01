package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MakeRepo extends JpaRepository<Make, Long> {
    @Query("select m from Make m where upper(m.nome) = upper(?1)")
    Optional<Make> findByNome(String nome);




}
