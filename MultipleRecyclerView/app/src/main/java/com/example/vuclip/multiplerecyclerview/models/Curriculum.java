package com.example.vuclip.multiplerecyclerview.models;

import java.util.List;

/**
 * Created by Banty on 05/05/18.
 * Model POJO class
 */
public class Curriculum {
    private String year;
    private List<String> subjectList;

    public Curriculum(String year, List<String> subjectList) {
        this.year = year;
        this.subjectList = subjectList;
    }

    public String getYear() {
        return year;
    }

    public List<String> getSubjectList() {
        return subjectList;
    }
}
