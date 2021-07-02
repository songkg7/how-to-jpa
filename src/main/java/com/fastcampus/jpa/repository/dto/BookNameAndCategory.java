package com.fastcampus.jpa.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookNameAndCategory {

    private String name;
    private String category;

//    String getName();

//    String getCategory();

}
