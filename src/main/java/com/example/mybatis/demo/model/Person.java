package com.example.mybatis.demo.model;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Person {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String country;


    private Gender gender;

    @Getter
    @AllArgsConstructor
    public enum Gender{
        MAN(1,"男"),
        WOMEN(2,"女");

        @EnumValue
        private int id;
        private String gender;
    }
}
