package com.example.demo.model.req;

import lombok.AllArgsConstructor;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author senreysong
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageBase {
  @Default private Integer pageIndex = 0;

  @Default private Integer pageSize = 15;

  public int getOffset() {
    return this.pageIndex * this.pageSize;
  }
}
