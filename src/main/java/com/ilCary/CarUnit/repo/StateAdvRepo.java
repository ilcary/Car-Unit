package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.StateAdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateAdvRepo extends JpaRepository<StateAdv,Long> {
}
