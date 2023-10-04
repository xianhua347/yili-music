package com.bilitech.yilimusic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
  MALE(1, "男"),
  FEMALE(2, "女"),
  UNKNOWN(3, "未知");

  private final Integer value;

  private final String display;
}
