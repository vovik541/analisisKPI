package com.analysis.data.repository;


import com.analysis.data.entity.CountriesDimension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountriesDimension, Long> {
    CountriesDimension findByCountryName(String countryName);
}