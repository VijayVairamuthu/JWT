package com.example.allocation.repository;

import com.example.allocation.entity.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<Runner, Integer> {
}
