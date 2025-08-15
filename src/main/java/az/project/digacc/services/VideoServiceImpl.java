package az.project.digacc.services;

import az.project.digacc.dto.VideoDto;
import az.project.digacc.entity.CourseEntity;
import az.project.digacc.entity.VideoEntity;
import az.project.digacc.repository.CourseRepository;
import az.project.digacc.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<VideoDto> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoDto> getVideosByCourseId(Long courseId) {
        return videoRepository.findByCourseId(courseId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDto getVideoById(Long id) {
        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));
        return mapToDto(video);
    }

    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        CourseEntity course = courseRepository.findById(videoDto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        VideoEntity video = VideoEntity.builder()
                .course(course)
                .title(videoDto.getTitle())
                .description(videoDto.getDescription())
                .videoUrl(videoDto.getVideoUrl())
                .build();

        VideoEntity savedVideo = videoRepository.save(video);
        return mapToDto(savedVideo);
    }

    @Override
    public VideoDto updateVideo(Long id, VideoDto videoDto) {
        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Video not found"));

        if (videoDto.getCourseId() != null) {
            CourseEntity course = courseRepository.findById(videoDto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            video.setCourse(course);
        }

        video.setTitle(videoDto.getTitle());
        video.setDescription(videoDto.getDescription());
        video.setVideoUrl(videoDto.getVideoUrl());

        VideoEntity updatedVideo = videoRepository.save(video);
        return mapToDto(updatedVideo);
    }

    @Override
    public void deleteVideo(Long id) {
        if (!videoRepository.existsById(id)) {
            throw new RuntimeException("Video not found");
        }
        videoRepository.deleteById(id);
    }

    private VideoDto mapToDto(VideoEntity video) {
        return new VideoDto(
                video.getId(),
                video.getCourse().getId(),
                video.getTitle(),
                video.getDescription(),
                video.getVideoUrl()
        );
    }
}
