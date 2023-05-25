package com.analysis.data.repository;

import com.analysis.data.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionFactsRepository extends JpaRepository<ConditionFacts, Long> {

    ConditionFacts findByYearsDimensionAndCountriesDimension(YearsDimension yearsDimension, CountriesDimension countriesDimension);
}
