package com.analysis.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ConditionFacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "conditions_facts")
public class ConditionFacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name="year_id")
    private YearsDimension yearsDimension;
    @ManyToOne
    @JoinColumn(name="country_id")
    private CountriesDimension countriesDimension;

//    @ManyToOne
//    @JoinColumn(name="urbanisation_id")
//    private UrbanisationDimension urbanisationDimension;
    @Column(name = "basic_sanitization_services_rate")
    private Float basicSanitizationServices;

    @Column(name = "basic_hand_washing_rate")
    private Float basicHandWashingRate;

    @Column(name = "dentists")
    private Float dentists;
    @Column(name = "malaria_incidents")
    private Float malariaIncidents;

    @Column(name = "medical_doctors")
    private Float medicalDoctors;
}
