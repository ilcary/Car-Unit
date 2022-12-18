package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.Dealership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealershipRepo extends JpaRepository<Dealership, Long> {
    @Query("select d from Dealership d where d.ceo.id = ?1")
    Optional<Dealership> findByCeo_Id(long id);

}
