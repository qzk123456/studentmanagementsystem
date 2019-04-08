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
public class ClassVo implements Serializable{

    private Integer id;

    private String className;
}
