package com.example.demo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "user")
// 逻辑删除
@SQLDelete(sql = "update user set deleted = true where id = ?")
@Where(clause = "deleted = false")
@DynamicUpdate
// lombok
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class UserDO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  //  枚举类型
  @Enumerated(value = EnumType.STRING)
  private Sex sex;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "tel")
  private String tel;

  @Column(name = "address")
  private String address;

  @Column(name = "deleted", nullable = false, updatable = false)
  private Boolean deleted;

  @PrePersist
  public void prePersist() {
    if (deleted == null) {
      deleted = false;
    }
  }
}
