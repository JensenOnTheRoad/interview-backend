package com.example.demo.dao;
/**
 * @author jensen_deng
 */
public enum Sex {
  MALE("男性"),
  FEMALE("女性");

  Sex(String desc) {
    this.desc = desc;
  }

  public final String desc;
}
