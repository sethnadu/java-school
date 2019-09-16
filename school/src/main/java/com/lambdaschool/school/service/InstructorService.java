package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Instructor;

import java.util.ArrayList;

public interface InstructorService
{
    ArrayList<Instructor> findAll();

    void delete(long id);

    Instructor save(Instructor instructor);

    Instructor update(Instructor instructor, long id);
}
