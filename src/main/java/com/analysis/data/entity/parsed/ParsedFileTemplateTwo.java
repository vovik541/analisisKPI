package com.analysis.data.entity.parsed;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class ParsedFileTemplateTwo {
    @CsvBindByPosition(position = 0)
    private String country;

    @CsvBindByPosition(position = 2)
    private Integer year;

    @CsvBindByPosition(position = 3)
    private String sex;

    @CsvBindByPosition(position = 4)
    private Float percent;
}
