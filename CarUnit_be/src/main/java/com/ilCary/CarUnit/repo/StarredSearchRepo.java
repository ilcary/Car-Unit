package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.StarredSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarredSearchRepo extends JpaRepository<StarredSearch, Long> {
}
