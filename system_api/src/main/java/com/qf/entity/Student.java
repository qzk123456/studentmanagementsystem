package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{

    private Integer id;

    private String name;

    private Integer age;

    private Integer classId;

    private Boolean flag;
}
