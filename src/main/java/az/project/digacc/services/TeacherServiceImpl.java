package az.project.digacc.services;

import az.project.digacc.dto.TeacherDto;
import az.project.digacc.entity.TeacherEntity;
import az.project.digacc.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto getTeacherById(Long id) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return mapToDto(teacher);
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        TeacherEntity teacher = TeacherEntity.builder()
                .firstName(teacherDto.getFirstName())
                .lastName(teacherDto.getLastName())
                .bio(teacherDto.getBio())
                .photoUrl(teacherDto.getPhotoUrl())
                .build();

        TeacherEntity savedTeacher = teacherRepository.save(teacher);
        return mapToDto(savedTeacher);
    }

    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setBio(teacherDto.getBio());
        teacher.setPhotoUrl(teacherDto.getPhotoUrl());

        TeacherEntity updatedTeacher = teacherRepository.save(teacher);
        return mapToDto(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found");
        }
        teacherRepository.deleteById(id);
    }

    private TeacherDto mapToDto(TeacherEntity teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getBio(),
                teacher.getPhotoUrl()
        );
    }
}