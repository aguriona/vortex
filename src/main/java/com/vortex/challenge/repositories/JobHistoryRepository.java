package com.vortex.challenge.repositories;

import com.vortex.challenge.entities.Employee;
import com.vortex.challenge.entities.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, Long> {
}
