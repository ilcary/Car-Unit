package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.DealCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealCarRepo extends JpaRepository<DealCar, Long> {
    @Query("select d from DealCar d where d.dealership.id = ?1")
    List<DealCar> findByDealership_Id(Long id);



}
