package com.analysis.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "countries_dimension")
public class CountriesDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "country_name")
    private String countryName;
    @OneToMany(mappedBy = "countriesDimension")
    private List<HealthFacts> healthFacts;
    @OneToMany(mappedBy = "countriesDimension")
    private List<ConditionFacts> conditionFacts;


    public CountriesDimension(String countryName) {
        this.countryName = countryName;
    }
}
