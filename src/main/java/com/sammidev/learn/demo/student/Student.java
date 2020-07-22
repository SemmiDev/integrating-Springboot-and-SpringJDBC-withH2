package com.sammidev.learn.demo.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Student {

    private Long id;
    private String name;
    private String nisn;

    // tanpa id
    public Student(String name, String nisn){
        this.name = name;
        this.nisn = nisn;
    }
}