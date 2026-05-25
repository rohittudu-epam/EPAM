package com.epam.de.genesis.pss.m1;

import java.util.Arrays;
import java.util.Comparator;

public class StudentSort {
    /**
     * https://autocode-next.lab.epam.com/courses/1372/syllabus/5600
     *
     * @param students
     * @return
     */
    public Student[] sortStudentsByGradeAndId(Student[] students) {
        return Arrays.stream(students)
                .sorted(
                        Comparator.comparing(Student::getGrade)
                                .thenComparing(Student::getId)
                )
                .toArray(Student[]::new);
    }
}
