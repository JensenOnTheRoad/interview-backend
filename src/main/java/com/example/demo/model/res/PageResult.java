package com.example.demo.model.res;

import lombok.Builder;
import lombok.Data;

/**
 * @author jensen_deng
 */
@Data
@Builder
public class PageResult<T> {

  private T dataList;

  private Long countTotal;
}
