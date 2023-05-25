package com.analysis.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "HealthFacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "health_facts")
public class HealthFacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name="sex_id")
    private SexesDimension sexesDimension;
    @ManyToOne
    @JoinColumn(name="year_id")
    private YearsDimension yearsDimension;
    @ManyToOne
    @JoinColumn(name="country_id")
    private CountriesDimension countriesDimension;

    @Column(name = "cancer_percent")
    private Float cancerPercent;
    @Column(name = "alcohol_abuse_percent")
    private Float alcoholConsumptionPercent;

    @Column(name = "life_expectancy_at_birth")
    private Float lifeExpectancyAtBirth;

    @Column(name = "smoking_abuse")
    private Float smokingAbusePercent;
    @Column(name = "crud_suicide_rate")
    private Float crudSuicideRate;
    @Column(name = "poisoning_mortality_rate")
    private Float poisoningMortalityRate;
    public HealthFacts(SexesDimension sexesDimension, YearsDimension yearsDimension, CountriesDimension countriesDimension, Float cancerPercent) {
        this.sexesDimension = sexesDimension;
        this.yearsDimension = yearsDimension;
        this.countriesDimension = countriesDimension;
        this.cancerPercent = cancerPercent;
    }
}
