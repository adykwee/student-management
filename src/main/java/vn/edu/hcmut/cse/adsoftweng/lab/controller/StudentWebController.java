package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService service;

    // Route: GET http://localhost:8080/students
    // Danh sach sinh vien
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        // 1. Lay du lieu tu Service
        List<Student> students = service.getAll();
        if (keyword != null && !keyword.isEmpty()) {
            students = service.searchByName(keyword);
        } else {
            students = service.getAll();
        }

        // 2. Dong goi du lieu vao "Model" de chuyen sang View
        model.addAttribute("dsSinhVien", students);

        // 3. Tra ve ten cua View
        return "students";
    }

    // Route: GET http://localhost:8080/students/create
    // Hien thi form them sinh vien
    @GetMapping("/create")
    public String showCreateForm() {
        return "create";
    }

    // Route: POST http://localhost:8080/students
    // Luu sinh vien moi
    @PostMapping
    public String createStudent(Student student, RedirectAttributes redirectAttributes) {
        service.save(student);
        redirectAttributes.addFlashAttribute("message", "Them sinh vien thanh cong!");
        return "redirect:/students";
    }

    // Route: GET http://localhost:8080/students/{id}
    // Hien thi chi tiet sinh vien
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable("id") String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "detail";
    }

    // Route: GET http://localhost:8080/students/{id}/edit
    // Hien thi form chinh sua sinh vien
    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Student student = service.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "update";
    }

    // Route: POST http://localhost:8080/students/{id}
    // Luu cap nhat sinh vien
    @PostMapping("/{id}")
    public String updateStudent(@PathVariable("id") String id, Student student, RedirectAttributes redirectAttributes) {
        student.setId(id);
        service.save(student);
        redirectAttributes.addFlashAttribute("message", "Cap nhat sinh vien thanh cong!");
        return "redirect:/students";
    }

    // Route: POST http://localhost:8080/students/{id}/delete
    // Xoa sinh vien
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("message", "Xoa sinh vien thanh cong!");
        return "redirect:/students";
    }
}
