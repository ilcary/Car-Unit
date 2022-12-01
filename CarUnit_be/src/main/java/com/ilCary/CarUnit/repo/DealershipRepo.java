package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepo extends JpaRepository<Dealership, Long> {
}
