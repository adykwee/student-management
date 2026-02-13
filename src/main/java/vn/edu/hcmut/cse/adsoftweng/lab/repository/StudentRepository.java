package vn.edu.hcmut.cse.adsoftweng.lab.repository;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository
                extends JpaRepository<Student, Integer> {
    List<Student> findByNameContainingIgnoreCase(String keyword);
}