package com.integrador.cv.dto;

import com.integrador.cv.entities.Headline;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {

    private Integer id;
    private String title;
    private String place;
    private String comments;
    private LocalDate startDate;
    private LocalDate endDate;
    private Headline headline;
    private Boolean status;
}
