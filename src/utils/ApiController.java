package utils;

import game.Game;
import game.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import student.Student;
import teacher.Teacher;

import java.util.List;

@RestController
@CrossOrigin
public class ApiController {

    private final DatabaseQueries querier;

    @Autowired
    public ApiController(final DatabaseQueries querier) {
        this.querier = querier;
    }

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public @ResponseBody ReturnTemplate<Student> getStudent(@RequestParam("student-id") final String studentId) {
        return new ReturnTemplate<Student>().validateAndProcessRequest(new InputValidator[]{new InputValidator(studentId)}, () -> this.querier.getStudent(studentId));
    }

    @RequestMapping(value = "student", method = RequestMethod.POST, params = {"student-id", "word-index", "word-solution"})
    public @ResponseBody ReturnTemplate<Student> getSolution(@RequestParam("student-id") final String studentId, @RequestParam("word-index") final int solvableWordIndex, @RequestParam("word-solution") final String studentSolution) {
        final Student student = this.querier.getStudent(studentId);
        return new ReturnTemplate<Student>().validateAndProcessRequest(new InputValidator[]{new SolutionInputValidator(studentSolution, solvableWordIndex, student)}, () -> {
            this.querier.updateStudentProgress(student);
            return student;
        });
    }


    @RequestMapping(value = "teacher", method = RequestMethod.POST)
    public @ResponseBody ReturnTemplate<Teacher> getTeacher(@RequestParam("teacher-code") final String teacherCode) {
        return new ReturnTemplate<Teacher>().validateAndProcessRequest(new InputValidator[]{new InputValidator(teacherCode)}, () -> this.querier.getTeacherByCode(teacherCode));
    }

    @RequestMapping(value = "teacher/students", method = RequestMethod.POST)
    public @ResponseBody ReturnTemplate<List<Student>> getTeacherStudents(@RequestParam("teacher-code") final String teacherCode) {
        return new ReturnTemplate<List<Student>>().validateAndProcessRequest(new InputValidator[]{new InputValidator(teacherCode)}, () -> this.querier.getStudentsByTeacherCode(teacherCode));
    }

    @RequestMapping(value = "teacher/game", method = RequestMethod.POST)
    public @ResponseBody ReturnTemplate<Game> getTeacherGame(@RequestParam("teacher-code") final String teacherCode) {
        return new ReturnTemplate<Game>().validateAndProcessRequest(new InputValidator[]{new InputValidator(teacherCode)}, () -> this.querier.getGameByTeacherCode(teacherCode));
    }

}