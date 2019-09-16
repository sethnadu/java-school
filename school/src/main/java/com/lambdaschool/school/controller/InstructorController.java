package com.lambdaschool.school.controller;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/instructors")
public class InstructorController
{
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private InstructorService instructorService;

    // GET: localhost:2019/instructors/instructors
    @GetMapping(value = "/instructors", produces = {"application/json"})
    public ResponseEntity<?> listAllInstructors(HttpServletRequest request)
    {
        logger.warn(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    // POST: localhost:2019:/instructors/instructors
    @PostMapping(value = "/instructors", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addInstructor(@Valid @RequestBody Instructor newInstructor, HttpServletRequest request) throws URISyntaxException
    {
        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/instructors/instructors/{instructid}").buildAndExpand(newInstructor.getInstructid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //  DELETE: localhost:2019/instructors/instructors/{instructid}
    @DeleteMapping(value = "/instructors/{instructid}")
    public ResponseEntity<?> deleteInstructorById(
            @PathVariable
                    long instructid)
    {
        instructorService.delete(instructid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //  PUT: localhost:2019/instructors/instructors/{instructid}
    @PutMapping(value = "/instructors/{instructid}",
                produces = {"application/json"},
                consumes = {"application/json"})
    public ResponseEntity<?> updateInstructor(
            @RequestBody
                    Instructor updateInstructor,
            @PathVariable
                    long instructid)
    {
        instructorService.update(updateInstructor, instructid);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
