package com.example.demo.model.req;

import com.example.demo.dao.Sex;
import com.example.demo.dao.UserDO;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author jensen_deng
 */
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserAddReq extends PageBase {

  private Long id;

  @NotBlank private String name;

  @NotNull private Integer age;

  @NotBlank private String address;

  @NotBlank private String sex;

  @NotNull private String tel;

  public UserDO to() {
    return UserDO.builder()
        .id(this.getId())
        .name(this.getName())
        .tel(this.getTel())
        .age(this.getAge())
        .sex("男".equals(this.getSex()) ? Sex.MALE : Sex.FEMALE)
        .address(this.getAddress())
        .build();
  }
}
