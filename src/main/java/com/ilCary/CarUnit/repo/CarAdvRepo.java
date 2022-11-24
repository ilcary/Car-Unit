package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.CarAdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarAdvRepo extends JpaRepository<CarAdv,Long> {
}
