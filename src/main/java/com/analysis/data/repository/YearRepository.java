package com.analysis.data.repository;

import com.analysis.data.entity.YearsDimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<YearsDimension, Long> {
    YearsDimension findByYear(Integer year);
}
