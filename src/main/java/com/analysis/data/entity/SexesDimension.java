package com.analysis.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "SexesDimension")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sexes_dimension")
public class SexesDimension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(name = "sex_name")
    private String sexName;

    @OneToMany(mappedBy = "sexesDimension")
    private List<HealthFacts> healthFacts;

    public SexesDimension(String countryName) {
        this.sexName = countryName;
    }
}
