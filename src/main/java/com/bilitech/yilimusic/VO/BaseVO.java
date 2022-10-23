package com.bilitech.yilimusic.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseVO {
    private String id;

    @JsonFormat(timezone = "GMT-8",pattern = "yyyyMMddHHmmss")
    private Date createdTime;

    @JsonFormat(timezone = "GMT-8",pattern = "yyyyMMddHHmmss")
    private Date updatedTime;
}
