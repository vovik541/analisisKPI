package com.analysis.data.service;

import com.analysis.data.entity.*;
import com.analysis.data.entity.parsed.*;
import com.analysis.data.repository.*;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ETLService {
    private final String RESOURCES_PATH = "src/main/resources/data/";
    private final CountryRepository countryRepository;
    private final SexRepository sexRepository;
    private final YearRepository yearRepository;
    private final HealthFactsRepository healthFactsRepository;
    private final ConditionFactsRepository conditionFactsRepository;

    private CountriesDimension countriesDimension;
    private YearsDimension yearsDimension;
    private SexesDimension sexesDimension;
    private HealthFacts healthFacts;

    private ConditionFacts conditionFacts;
    private List<ParsedFileTemplateOne> parsedTOne;
    private List<ParsedFileTemplateTwo> parsedTTwo;
    private List<ParsedFileTemplateThree> parsedTThree;
    private List<ParsedFileTemplateFour> parsedTFour;
    private List<ParsedFileTemplateFive> parsedTFive;

    public void extractTransformAndLoadData() {

        fillInSexesData();
        fillInCancerData();
        fillInAlcoholData();
        fillInLifeExpectancyAtBirthData();
        fillInSmokingData();
        fillInSuicideRateData();
        fillInMortalityRatePoisoningData();

        fillInBasicSanitizationData();
        fillInBasicHandWashingData();
        fillInDentistsData();
        fillInIncidentsOfMalariaData();
        fillInMedicalDoctorsData();
    }

    private void fillInSexesData() {
        if (sexRepository.findBySexName("Female") == null) {
            sexRepository.save(new SexesDimension("Male"));
            sexRepository.save(new SexesDimension("Female"));
            sexRepository.save(new SexesDimension("Both"));
        }

    }
    private void fillInMedicalDoctorsData() {
        parsedTFour = parseFileTemplateFour("medicalDoctors.csv");

        for (ParsedFileTemplateFour element : parsedTFour) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            conditionFacts = conditionFactsRepository.findByYearsDimensionAndCountriesDimension(yearsDimension, countriesDimension);

            if (conditionFacts == null) {
                conditionFacts = ConditionFacts.builder()
                        .medicalDoctors(element.getPercent())
                        .countriesDimension(countriesDimension)
                        .yearsDimension(yearsDimension)
                        .build();
                conditionFactsRepository.save(conditionFacts);
            } else {
                conditionFacts.setMedicalDoctors(element.getPercent());
                conditionFactsRepository.save(conditionFacts);
            }
        }

    }
    private void fillInIncidentsOfMalariaData() {
        parsedTFive = parseFileTemplateFive("incedenceOfMalaria.csv");

        for (ParsedFileTemplateFive element : parsedTFive) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            conditionFacts = conditionFactsRepository.findByYearsDimensionAndCountriesDimension(yearsDimension, countriesDimension);

            if (conditionFacts == null) {
                conditionFacts = ConditionFacts.builder()
                        .malariaIncidents(element.getPercent())
                        .countriesDimension(countriesDimension)
                        .yearsDimension(yearsDimension)
                        .build();
                conditionFactsRepository.save(conditionFacts);
            } else {
                conditionFacts.setMalariaIncidents(element.getPercent());
                conditionFactsRepository.save(conditionFacts);
            }
        }

    }
    private void fillInDentistsData() {
        parsedTFour = parseFileTemplateFour("dentists.csv");

        for (ParsedFileTemplateFour element : parsedTFour) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            conditionFacts = conditionFactsRepository.findByYearsDimensionAndCountriesDimension(yearsDimension, countriesDimension);

            if (conditionFacts == null) {
                conditionFacts = ConditionFacts.builder()
                        .dentists(element.getPercent())
                        .countriesDimension(countriesDimension)
                        .yearsDimension(yearsDimension)
                        .build();
                conditionFactsRepository.save(conditionFacts);
            } else {
                conditionFacts.setDentists(element.getPercent());
                conditionFactsRepository.save(conditionFacts);
            }
        }

    }

    private void fillInBasicHandWashingData() {
        parsedTThree = parseFileTemplateThree("basicHandWashing.csv");

        for (ParsedFileTemplateThree element : parsedTThree) {
            if (element.getUrbanisation().toUpperCase().contains("TOTAL")) {
                checkCountry(element.getCountry());
                checkYear(element.getYear());

                conditionFacts = conditionFactsRepository.findByYearsDimensionAndCountriesDimension(yearsDimension, countriesDimension);

                if (conditionFacts == null) {
                    conditionFacts = ConditionFacts.builder()
                            .basicHandWashingRate(element.getPercent())
                            .countriesDimension(countriesDimension)
                            .yearsDimension(yearsDimension)
                            .build();
                    conditionFactsRepository.save(conditionFacts);
                } else {
                    conditionFacts.setBasicHandWashingRate(element.getPercent());
                    conditionFactsRepository.save(conditionFacts);
                }
            }
        }
    }

    private void fillInBasicSanitizationData() {
        parsedTThree = parseFileTemplateThree("atLeastBasicSanitizationServices.csv");

        for (ParsedFileTemplateThree element : parsedTThree) {
            if (element.getUrbanisation().toUpperCase().contains("TOTAL")) {
                checkCountry(element.getCountry());
                checkYear(element.getYear());

                conditionFacts = conditionFactsRepository.findByYearsDimensionAndCountriesDimension(yearsDimension, countriesDimension);

                if (conditionFacts == null) {
                    conditionFacts = ConditionFacts.builder()
                            .basicSanitizationServices(element.getPercent())
                            .countriesDimension(countriesDimension)
                            .yearsDimension(yearsDimension)
                            .build();
                    conditionFactsRepository.save(conditionFacts);
                } else {
                    conditionFacts.setBasicSanitizationServices(element.getPercent());
                    conditionFactsRepository.save(conditionFacts);
                }
            }
        }
    }

    private void fillInSmokingData() {
        parsedTTwo = parseFileTemplateTwo("tobaccoAge15.csv");

        for (ParsedFileTemplateTwo element : parsedTTwo) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            sexesDimension = sexRepository.findBySexName(parseSex(element.getSex()));
            healthFacts = healthFactsRepository.findBySexesDimensionAndYearsDimensionAndCountriesDimension(sexesDimension, yearsDimension, countriesDimension);

            if (healthFacts == null) {
                healthFacts = HealthFacts.builder()
                        .sexesDimension(sexesDimension)
                        .yearsDimension(yearsDimension)
                        .countriesDimension(countriesDimension)
                        .smokingAbusePercent(element.getPercent())
                        .build();
                healthFactsRepository.save(healthFacts);
            } else {
                healthFacts.setSmokingAbusePercent(element.getPercent());
                healthFactsRepository.save(healthFacts);
            }
        }
    }

    private void fillInMortalityRatePoisoningData() {
        parsedTTwo = parseFileTemplateTwo("mortalityRatePoisoning.csv");

        for (ParsedFileTemplateTwo element : parsedTTwo) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            sexesDimension = sexRepository.findBySexName(parseSex(element.getSex()));
            healthFacts = healthFactsRepository.findBySexesDimensionAndYearsDimensionAndCountriesDimension(sexesDimension, yearsDimension, countriesDimension);

            if (healthFacts == null) {
                healthFacts = HealthFacts.builder()
                        .sexesDimension(sexesDimension)
                        .yearsDimension(yearsDimension)
                        .countriesDimension(countriesDimension)
                        .poisoningMortalityRate(element.getPercent())
                        .build();
                healthFactsRepository.save(healthFacts);
            } else {
                healthFacts.setPoisoningMortalityRate(element.getPercent());
                healthFactsRepository.save(healthFacts);
            }
        }
    }

    private void fillInSuicideRateData() {
        parsedTOne = parseFileTemplateOne("crudeSuicideRates.csv");

        for (ParsedFileTemplateOne element : parsedTOne) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            sexesDimension = sexRepository.findBySexName(parseSex(element.getSex()));
            healthFacts = healthFactsRepository.findBySexesDimensionAndYearsDimensionAndCountriesDimension(sexesDimension, yearsDimension, countriesDimension);

            if (healthFacts == null) {
                healthFacts = HealthFacts.builder()
                        .sexesDimension(sexesDimension)
                        .yearsDimension(yearsDimension)
                        .countriesDimension(countriesDimension)
                        .crudSuicideRate(element.getPercent())
                        .build();
                healthFactsRepository.save(healthFacts);
            } else {
                healthFacts.setCrudSuicideRate(element.getPercent());
                healthFactsRepository.save(healthFacts);
            }
        }
    }

    private void fillInAlcoholData() {
        parsedTOne = parseFileTemplateOne("alcoholSubstanceAbuse.csv");

        for (ParsedFileTemplateOne element : parsedTOne) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            sexesDimension = sexRepository.findBySexName(parseSex(element.getSex()));
            healthFacts = healthFactsRepository.findBySexesDimensionAndYearsDimensionAndCountriesDimension(sexesDimension, yearsDimension, countriesDimension);

            if (healthFacts == null) {
                healthFacts = HealthFacts.builder()
                        .sexesDimension(sexesDimension)
                        .yearsDimension(yearsDimension)
                        .countriesDimension(countriesDimension)
                        .alcoholConsumptionPercent(element.getPercent())
                        .build();
                healthFactsRepository.save(healthFacts);
            } else {
                healthFacts.setAlcoholConsumptionPercent(element.getPercent());
                healthFactsRepository.save(healthFacts);
            }
        }
    }

    private void fillInLifeExpectancyAtBirthData() {
        parsedTOne = parseFileTemplateOne("lifeExpectancyAtBirth.csv");

        for (ParsedFileTemplateOne element : parsedTOne) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            sexesDimension = sexRepository.findBySexName(parseSex(element.getSex()));
            healthFacts = healthFactsRepository.findBySexesDimensionAndYearsDimensionAndCountriesDimension(sexesDimension, yearsDimension, countriesDimension);

            if (healthFacts == null) {
                healthFacts = HealthFacts.builder()
                        .sexesDimension(sexesDimension)
                        .yearsDimension(yearsDimension)
                        .countriesDimension(countriesDimension)
                        .lifeExpectancyAtBirth(element.getPercent())
                        .build();
                healthFactsRepository.save(healthFacts);
            } else {
                healthFacts.setLifeExpectancyAtBirth(element.getPercent());
                healthFactsRepository.save(healthFacts);
            }
        }
    }

    private void fillInCancerData() {
        parsedTOne = parseFileTemplateOne("30-70cancerChdEtc.csv");

        for (ParsedFileTemplateOne element : parsedTOne) {
            checkCountry(element.getCountry());
            checkYear(element.getYear());

            sexesDimension = sexRepository.findBySexName(parseSex(element.getSex()));

            healthFacts = healthFactsRepository.findBySexesDimensionAndYearsDimensionAndCountriesDimension(sexesDimension, yearsDimension, countriesDimension);

            if (healthFacts == null) {
                healthFacts = new HealthFacts(sexesDimension, yearsDimension, countriesDimension, element.getPercent());
                healthFactsRepository.save(healthFacts);
            }
        }
    }

    private void checkCountry(String country) {
        countriesDimension = countryRepository.findByCountryName(country);

        if (countriesDimension == null) {
            countriesDimension = new CountriesDimension(country);
            countryRepository.save(countriesDimension);
        }
    }

    private void checkYear(Integer year) {
        yearsDimension = yearRepository.findByYear(year);

        if (yearsDimension == null) {
            yearsDimension = new YearsDimension(year);
            yearRepository.save(yearsDimension);
        }
    }

    private List<ParsedFileTemplateThree> parseFileTemplateThree(String fileName) {
        String filePath = RESOURCES_PATH + fileName;
        List<ParsedFileTemplateThree> parsed;
        try {
            parsed = new CsvToBeanBuilder(new FileReader(filePath))
                    .withSkipLines(1)
                    .withType(ParsedFileTemplateThree.class)
                    .build()
                    .parse();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }
    private List<ParsedFileTemplateFour> parseFileTemplateFour(String fileName) {
        String filePath = RESOURCES_PATH + fileName;
        List<ParsedFileTemplateFour> parsed;
        try {
            parsed = new CsvToBeanBuilder(new FileReader(filePath))
                    .withSkipLines(1)
                    .withType(ParsedFileTemplateFour.class)
                    .build()
                    .parse();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }
    private List<ParsedFileTemplateFive> parseFileTemplateFive(String fileName) {
        String filePath = RESOURCES_PATH + fileName;
        List<ParsedFileTemplateFive> parsed;
        try {
            parsed = new CsvToBeanBuilder(new FileReader(filePath))
                    .withSkipLines(1)
                    .withType(ParsedFileTemplateFive.class)
                    .build()
                    .parse();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }

    private List<ParsedFileTemplateOne> parseFileTemplateOne(String fileName) {
        String filePath = RESOURCES_PATH + fileName;
        List<ParsedFileTemplateOne> parsed;
        try {
            parsed = new CsvToBeanBuilder(new FileReader(filePath))
                    .withSkipLines(1)
                    .withType(ParsedFileTemplateOne.class)
                    .build()
                    .parse();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }

    private List<ParsedFileTemplateTwo> parseFileTemplateTwo(String fileName) {
        String filePath = RESOURCES_PATH + fileName;
        List<ParsedFileTemplateTwo> parsed;
        try {
            parsed = new CsvToBeanBuilder(new FileReader(filePath))
                    .withSkipLines(1)
                    .withType(ParsedFileTemplateTwo.class)
                    .build()
                    .parse();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return parsed;
    }

    private String parseSex(String toParse) {
        String upper = toParse.toUpperCase();
        if (upper.contains("BOTH")) {
            return "Both";
        } else if (upper.contains("FEMALE")) {
            return "Female";
        } else {
            return "Male";
        }
    }

}
