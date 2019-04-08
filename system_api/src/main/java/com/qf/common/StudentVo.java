package com.qf.common;

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
public class StudentVo implements Serializable{

    private Integer id;

    private String name;

    private Integer age;

    private Integer classId;

    private String className;

    private Boolean flag;
}
