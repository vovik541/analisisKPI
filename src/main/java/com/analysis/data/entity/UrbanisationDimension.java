//package com.analysis.data.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "urbanisation_dimension")
//public class UrbanisationDimension {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private Long id;
//    @Column(name = "urbanisation_name")
//    private String urbanisationName;
//    @OneToMany(mappedBy = "urbanisationDimension")
//    private List<ConditionFacts> conditionFacts;
//
//    public UrbanisationDimension(String urbanisationName) {
//        this.urbanisationName = urbanisationName;
//    }
//}
