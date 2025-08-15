package az.project.digacc.services;

import az.project.digacc.dto.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {
    Page<CourseDto> getAllCourses(Pageable pageable);
    CourseDto getCourseById(Long id);
    CourseDto createCourse(CourseDto courseDto);
    CourseDto updateCourse(Long id, CourseDto courseDto);
    void deleteCourse(Long id);
    public List<CourseDto> searchCourses(String query);
}
