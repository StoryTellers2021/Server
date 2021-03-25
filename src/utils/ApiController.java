package utils;

import org.springframework.web.bind.annotation.*;
import student.Student;

import java.util.List;


@RestController
@CrossOrigin
public class ApiController {

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public @ResponseBody ReturnTemplate<Student> getStudent(@RequestParam("student-id") final String studentId) {
        return new ReturnTemplate<Student>().validateAndProcessRequest(new InputValidator[]{new InputValidator(studentId)}, () -> DatabaseStaticHandler.getStudent(studentId));
    }


    
}