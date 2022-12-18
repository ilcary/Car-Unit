package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.StarredSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarredSearchRepo extends JpaRepository<StarredSearch, Long> {
    @Query("select s from StarredSearch s where s.user.id = ?1")
    List<StarredSearch> findByUser_Id(long id);



}
