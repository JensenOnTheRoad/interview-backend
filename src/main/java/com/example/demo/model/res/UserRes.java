package com.example.demo.model.res;

import com.example.demo.dao.Sex;
import com.example.demo.dao.UserDO;
import lombok.Builder;
import lombok.Data;

/**
 * @author jensen_deng
 */
@Data
@Builder
public class UserRes {

  private Long id;

  private String name;

  private Integer age;

  private String address;

  private String sex;

  private String tel;

  public static UserRes from(UserDO userDO) {
    return UserRes.builder()
        .id(userDO.getId())
        .name(userDO.getName())
        .age(userDO.getAge())
        .sex(Sex.MALE.equals(userDO.getSex()) ? "男" : "女")
        .tel(userDO.getTel())
        .address(userDO.getAddress())
        .build();
  }
}
