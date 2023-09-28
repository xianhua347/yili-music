package com.bilitech.yilimusic.utils.jpa.converter;

import com.bilitech.yilimusic.enums.Gender;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {
  @Override
  public Integer convertToDatabaseColumn(Gender gender) {
    return gender.getValue();
  }

  @Override
  public Gender convertToEntityAttribute(Integer value) {
    return Option.of(value)
        .flatMap(val -> Stream.of(Gender.values())
            .find(gender -> val.equals(gender.getValue())))
        .getOrElseThrow(() -> new IllegalArgumentException("Unknown value: " + value));
  }
}
