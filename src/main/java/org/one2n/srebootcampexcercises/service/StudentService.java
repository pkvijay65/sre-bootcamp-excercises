package org.one2n.srebootcampexcercises.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.one2n.srebootcampexcercises.Exceptions.ResourceNotFoundException;
import org.one2n.srebootcampexcercises.Exceptions.StudentAlreadyExistsException;
import org.one2n.srebootcampexcercises.dtos.StudentDTO;
import org.one2n.srebootcampexcercises.model.Student;
import org.one2n.srebootcampexcercises.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Student getStudentById(Long id) {
        log.info("Fetching student with id: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    @Transactional
    public Student createStudent(StudentDTO studentDTO) {
        log.info("Creating new student with email: {}", studentDTO.getEmail());
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new StudentAlreadyExistsException("Student already exists with email: " + studentDTO.getEmail());
        }

        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setCourseOfStudy(studentDTO.getCourseOfStudy());

        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(Long id, StudentDTO studentDTO) {
        log.info("Updating student with id: {}", id);
        Student student = getStudentById(id);

        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setCourseOfStudy(studentDTO.getCourseOfStudy());

        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        log.info("Deleting student with id: {}", id);
        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}

