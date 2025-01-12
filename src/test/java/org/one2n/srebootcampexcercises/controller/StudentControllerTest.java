package org.one2n.srebootcampexcercises.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.one2n.srebootcampexcercises.dtos.StudentDTO;
import org.one2n.srebootcampexcercises.model.Student;
import org.one2n.srebootcampexcercises.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;
    private StudentDTO studentDTO;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "John", "Doe", "john@example.com", "Computer Science");
        studentDTO = new StudentDTO();
        studentDTO.setFirstName("John");
        studentDTO.setLastName("Doe");
        studentDTO.setEmail("john@example.com");
        studentDTO.setCourseOfStudy("Computer Science");
    }

    @Test
    void getAllStudents_ShouldReturnListOfStudents() throws Exception {
        List<Student> students = Arrays.asList(student);
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(student.getId()))
                .andExpect(jsonPath("$[0].firstName").value(student.getFirstName()));
    }

    @Test
    void getStudentById_ShouldReturnStudent() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(get("/api/v1/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()));
    }

    @Test
    void createStudent_ShouldReturnCreatedStudent() throws Exception {
        when(studentService.createStudent(any(StudentDTO.class))).thenReturn(student);

        mockMvc.perform(post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()));
    }

    @Test
    void updateStudent_ShouldReturnUpdatedStudent() throws Exception {
        when(studentService.updateStudent(eq(1L), any(StudentDTO.class))).thenReturn(student);

        mockMvc.perform(put("/api/v1/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()));
    }

    @Test
    void deleteStudent_ShouldReturnNoContent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/api/v1/students/1"))
                .andExpect(status().isNoContent());
    }
}
