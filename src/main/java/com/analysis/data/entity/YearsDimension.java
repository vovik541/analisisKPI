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
@Table(name = "years_dimension")
public class YearsDimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "year")
    private Integer year;
    @OneToMany(mappedBy = "yearsDimension")
    private List<HealthFacts> healthFacts;
    @OneToMany(mappedBy = "yearsDimension")
    private List<ConditionFacts> conditionFacts;
    public YearsDimension(Integer year) {
        this.year = year;
    }
}
