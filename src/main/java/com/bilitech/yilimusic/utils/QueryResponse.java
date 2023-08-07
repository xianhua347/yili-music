package com.bilitech.yilimusic.utils;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryResponse<T> {

  private List<T> list;

  private Long total;

  private Integer totalPage;

  /**
   * 当前页
   */
  private Integer page;

  /**
   * 每页条数
   */
  private Integer size;

  private QueryResponse(List<T> list, Long total, Integer totalPage, Integer page, Integer size) {
    this.total = total;
    this.totalPage = totalPage;
    this.page = page;
    this.size = size;
    this.list = list;
  }

  /**
   * 构造分页结果
   *
   * @param total     总条数
   * @param totalPage 总页数
   * @param size      每页条数
   * @param page      当前页
   * @param list      数据
   * @param <T>       数据类型
   * @return 分页结果
   */

  public static <T> QueryResponse<T> of(Long total, Integer totalPage, Integer page, Integer size,
      List<T> list) {
    return new QueryResponse<>(list, total, totalPage, page + 1, size);
  }
}
