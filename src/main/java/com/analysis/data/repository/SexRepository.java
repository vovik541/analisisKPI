package com.analysis.data.repository;

import com.analysis.data.entity.SexesDimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SexRepository extends JpaRepository<SexesDimension, Long> {

    SexesDimension findBySexName(String sexName);
}
