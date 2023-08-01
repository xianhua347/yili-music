package com.bilitech.yilimusic.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public abstract class BaseVO {

  private String id;

  @JsonFormat(timezone = "GMT-8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createdTime;

  @JsonFormat(timezone = "GMT-8", pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date updatedTime;
}
