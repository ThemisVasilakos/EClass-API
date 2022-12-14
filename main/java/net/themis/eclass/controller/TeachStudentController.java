package net.themis.eclass.controller;

import net.themis.eclass.dto.AnnouncementDTO;
import net.themis.eclass.dto.CourseDTO;
import net.themis.eclass.model.Course;
import net.themis.eclass.model.DAOUser;
import net.themis.eclass.dto.UserDTO;
import net.themis.eclass.service.CourseService;
import net.themis.eclass.service.UserService;
import net.themis.eclass.service.impl.AnnouncementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/eclass")
public class TeachStudentController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnnouncementServiceImpl announcementService;

    @GetMapping("/courses/get-all")
    public List<CourseDTO> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{courseId}/announcements")
    public List<AnnouncementDTO> getCourseAnnouncements(@PathVariable Long courseId){

        return announcementService.getCourseAnnouncements(courseId);
    }

    @GetMapping("/courses/{name}")
    public ResponseEntity<Course> getCourseByName(@PathVariable String name){
        return new ResponseEntity<Course>(courseService.findByCourseName(name), HttpStatus.OK );
    }

    @GetMapping("/home")
    public DAOUser home(){

        DAOUser user = userService.findByUserName();

        return user;
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> getCourseStudents(@RequestBody UserDTO user){

        userService.updateStudent(user,user.getUsername(),user.getPassword(),user.getFirstName(),user.getLastName()
                ,user.getEmail());

        return new ResponseEntity<DAOUser>(userService.findByUserName(), HttpStatus.OK );
    }

}
