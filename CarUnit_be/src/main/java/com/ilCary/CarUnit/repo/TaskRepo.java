package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.user.id = ?1")
    List<Task> findByUser_Id(long id);



}
