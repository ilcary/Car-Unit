package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
}
