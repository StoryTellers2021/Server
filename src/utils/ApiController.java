package utils;

import org.springframework.web.bind.annotation.*;
import student.Student;

import java.util.List;
// import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin
public class ApiController {
	
    @RequestMapping(value = "story-{storyIndex:[0-9]+}", method = RequestMethod.GET)
    public @ResponseBody StoryInfoDelegate getServerInfo(@PathVariable("storyIndex") final StoryInfoDelegate sid) {
        return sid;
    }

    @RequestMapping(value = "student-exists", method = RequestMethod.POST)
    public @ResponseBody ReturnTemplate<Boolean> userExists(@RequestParam("student-id") final String studentId) {
        return new ReturnTemplate<Boolean>().validateAndProcessRequest(new InputValidator(studentId), (final List<String> problems) -> DatabaseStaticHandler.studentIdIsValid(studentId));
    }

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public @ResponseBody ReturnTemplate<Student> getStudent(@RequestParam("student-id") final String studentId) {
        return new ReturnTemplate<Student>().validateAndProcessRequest(new InputValidator(studentId), (final List<String> problems) -> DatabaseStaticHandler.getStudent(studentId));
    }

    
}