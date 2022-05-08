package com.integrador.cv.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeaderDTO {

    private Integer id;
    private String name;
    private String description;
    private String aboutMe;
    private String phone;
    private String email;
    private String city;
}
