    package az.project.digacc.services;

    import az.project.digacc.dto.CourseDto;
    import az.project.digacc.entity.CourseEntity;
    import az.project.digacc.repository.CourseRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class CourseServiceImpl implements CourseService{
        private final CourseRepository courseRepository;

        @Override
        public Page<CourseDto> getAllCourses(Pageable pageable) {
            return courseRepository.findAll(pageable).map(this::mapToDto);
        }

        @Override
        public CourseDto getCourseById(Long id) {
            CourseEntity course = courseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            return mapToDto(course);
        }

        @Override
        public CourseDto createCourse(CourseDto courseDto) {
            CourseEntity course = CourseEntity.builder()
                    .title(courseDto.getTitle())
                    .description(courseDto.getDescription())
                    .createdAt(LocalDateTime.now())
                    .build();

            CourseEntity savedCourse = courseRepository.save(course);
            return mapToDto(savedCourse);
        }

        @Override
        public CourseDto updateCourse(Long id, CourseDto courseDto) {
            CourseEntity course = courseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            course.setTitle(courseDto.getTitle());
            course.setDescription(courseDto.getDescription());

            CourseEntity updatedCourse = courseRepository.save(course);
            return mapToDto(updatedCourse);
        }

        @Override
        public void deleteCourse(Long id) {
            if (!courseRepository.existsById(id)) {
                throw new RuntimeException("Course not found");
            }
            courseRepository.deleteById(id);
        }

        private CourseDto mapToDto(CourseEntity course) {
            return new CourseDto(
                    course.getId(),
                    course.getTitle(),
                    course.getDescription()
            );
        }

        private CourseEntity mapToEntity(CourseDto courseDto) {
            return CourseEntity.builder()
                    .title(courseDto.getTitle())
                    .description(courseDto.getDescription())
                    .build();
        }
        @Override
        public List<CourseDto> searchCourses(String query) {
            return courseRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query)
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }
    }
