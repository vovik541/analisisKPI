package com.analysis.data.repository;

import com.analysis.data.entity.CountriesDimension;
import com.analysis.data.entity.HealthFacts;
import com.analysis.data.entity.SexesDimension;
import com.analysis.data.entity.YearsDimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthFactsRepository extends JpaRepository<HealthFacts, Long> {

    HealthFacts findBySexesDimensionAndYearsDimensionAndCountriesDimension(SexesDimension sexesDimension, YearsDimension yearsDimension, CountriesDimension countriesDimension);

}
