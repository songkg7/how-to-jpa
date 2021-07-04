package com.fastcampus.jpa.domain;

import com.fastcampus.jpa.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//@Converter(autoApply = true)
@Converter
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {

    // NOTE: Converter 를 사용할 때는 데이터 유실을 방지하기 위해서 모든 방향을 구현해야 한다.
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }

}
