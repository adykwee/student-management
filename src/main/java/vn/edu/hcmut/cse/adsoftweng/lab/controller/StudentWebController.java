package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Lưu ý: dùng @Controller, KHÔNG dùng @RestController
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            // Can viet them ham searchByName trong Service/Repository
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);
        return "list";
    }

    // Route: GET http://localhost:8080/students/{id}
    @GetMapping("/{id}")
    public String getStudentById(@PathVariable Integer id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "detail";
    }

    // Route: GET http://localhost:8080/students/new
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("formMode", "create");
        return "form";
    }

    // Route: GET http://localhost:8080/students/{id}/edit
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("formMode", "edit");
        return "form";
    }

    // Route: POST http://localhost:8080/students
    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        service.save(student);
        return "redirect:/students";
    }

    // Route: POST http://localhost:8080/students/{id}
    @PostMapping("/{id}")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute Student student) {
        student.setId(id);
        service.save(student);
        return "redirect:/students";
    }

    // Route: POST http://localhost:8080/students/{id}/delete
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/students";
    }
}