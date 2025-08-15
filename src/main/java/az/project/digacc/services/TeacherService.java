package az.project.digacc.services;

import az.project.digacc.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAllTeachers();
    TeacherDto getTeacherById(Long id);
    TeacherDto createTeacher(TeacherDto teacherDto);
    TeacherDto updateTeacher(Long id, TeacherDto teacherDto);
    void deleteTeacher(Long id);
}
