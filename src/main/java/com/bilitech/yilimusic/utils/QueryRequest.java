package com.bilitech.yilimusic.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.ObjectUtils;

@Data
@Schema(name = "QueryRequest", description = "查询请求")
public class QueryRequest<T> {

  /**
   * 查询条件
   */
  @Schema(description = "查询条件")
  private T query;

  /**
   * 当前页
   */
  @Schema(description = "当前页")
  @Min(value = 1, message = "page最小值为1")
  private Integer page;


  /**
   * 每页条数
   */
  @Schema(description = "每页条数")
  @Min(value = 1, message = "page最小值为1")
  private Integer size;

  /**
   * 排序字段和排序方式
   */
  @Schema(description = "排序字段和排序方式")
  private Map<String, Direction> sorts;

  /**
   * @return 转换为Pageable
   */
  public Pageable toPage() {
    if (ObjectUtils.isEmpty(sorts)) {
      return PageRequest.of(page - 1, size);
    } else {
      return PageRequest.of(page - 1, size,
          Sort.by(sorts.entrySet().stream()
              .map(entry -> new Sort.Order(Direction.fromString(entry.getValue().name()), entry.getKey()))
              .collect(Collectors.toList())));
    }
  }
}
