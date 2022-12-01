package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepo extends JpaRepository<Comune,Long> {
}
