package com.analysis.data.entity.parsed;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ParsedFileTemplateThree {
    @CsvBindByPosition(position = 0)
    private String country;

    @CsvBindByPosition(position = 2)
    private Integer year;

    @CsvBindByPosition(position = 3)
    private String urbanisation;

    @CsvBindByPosition(position = 4)
    private Float percent;
}
