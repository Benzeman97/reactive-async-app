package com.benz.reactive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    private long id;
    private String ename;
    private String epfNum;
    private double salary;
    private Date date;

}
