package com.ilCary.CarUnit.repo;

import com.ilCary.CarUnit.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {
}
