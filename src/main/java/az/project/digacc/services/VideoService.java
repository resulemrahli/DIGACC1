package az.project.digacc.services;

import az.project.digacc.dto.VideoDto;

import java.util.List;

public interface VideoService {
    List<VideoDto> getAllVideos();
    List<VideoDto> getVideosByCourseId(Long courseId);
    VideoDto getVideoById(Long id);
    VideoDto createVideo(VideoDto videoDto);
    VideoDto updateVideo(Long id, VideoDto videoDto);
    void deleteVideo(Long id);
}
