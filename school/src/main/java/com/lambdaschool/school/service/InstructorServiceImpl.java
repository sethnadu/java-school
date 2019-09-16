package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructrepos;

    @Override
    public ArrayList<Instructor> findAll()
    {
        ArrayList<Instructor> instructorAll = new ArrayList<>();
        instructrepos.findAll().iterator().forEachRemaining(instructorAll::add);
        return instructorAll;
    }

    @Override
    public void delete(long id)
    {
        if (instructrepos.findById(id).isPresent())
        {
            instructrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Instructor save(Instructor instructor)
    {
        Instructor newInstructor = new Instructor();

        newInstructor.setInstructname(instructor.getInstructname());

        return instructrepos.save(newInstructor);
    }

    @Override
    public Instructor update(Instructor instructor, long id)
    {
        Instructor currentInstructor = instructrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if(instructor.getInstructname() != null)
        {
            currentInstructor.setInstructname(instructor.getInstructname());
        }

//        if (instructor.getCourses().size() > 0)
//        {
//            // adds new phone numbers to list
//            for (Courses c:instructor.getCourses())
//            {
//                currentZoo.getTelephones().add(new Telephone(t.getPhonetype(),t.getPhonenumber(), currentZoo));
//            }
//        }
        return instructrepos.save(currentInstructor);
    }
}
