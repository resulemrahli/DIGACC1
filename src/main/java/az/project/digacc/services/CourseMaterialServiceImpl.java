package az.project.digacc.services;

import az.project.digacc.dto.CourseMaterialDto;
import az.project.digacc.entity.CourseMaterialEntity;
import az.project.digacc.entity.VideoEntity;
import az.project.digacc.repository.CourseMaterialRepository;
import az.project.digacc.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseMaterialServiceImpl implements CourseMaterialService {
    private final CourseMaterialRepository courseMaterialRepository;
    private final VideoRepository videoRepository;

    @Override
    public List<CourseMaterialDto> getAllMaterials() {
        return courseMaterialRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseMaterialDto> getMaterialsByVideoId(Long videoId) {
        return courseMaterialRepository.findByVideoId(videoId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseMaterialDto getMaterialById(Long id) {
        CourseMaterialEntity material = courseMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course material not found"));
        return mapToDto(material);
    }

    @Override
    public CourseMaterialDto createMaterial(CourseMaterialDto materialDto) {
        VideoEntity video = videoRepository.findById(materialDto.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        CourseMaterialEntity material = CourseMaterialEntity.builder()
                .video(video)
                .title(materialDto.getTitle())
                .materialUrl(materialDto.getMaterialUrl())
                .build();

        CourseMaterialEntity savedMaterial = courseMaterialRepository.save(material);
        return mapToDto(savedMaterial);
    }

    @Override
    public CourseMaterialDto updateMaterial(Long id, CourseMaterialDto materialDto) {
        CourseMaterialEntity material = courseMaterialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course material not found"));

        if (materialDto.getVideoId() != null) {
            VideoEntity video = videoRepository.findById(materialDto.getVideoId())
                    .orElseThrow(() -> new RuntimeException("Video not found"));
            material.setVideo(video);
        }

        material.setTitle(materialDto.getTitle());
        material.setMaterialUrl(materialDto.getMaterialUrl());

        CourseMaterialEntity updatedMaterial = courseMaterialRepository.save(material);
        return mapToDto(updatedMaterial);
    }

    @Override
    public void deleteMaterial(Long id) {
        if (!courseMaterialRepository.existsById(id)) {
            throw new RuntimeException("Course material not found");
        }
        courseMaterialRepository.deleteById(id);
    }

    private CourseMaterialDto mapToDto(CourseMaterialEntity material) {
        return new CourseMaterialDto(
                material.getId(),
                material.getVideo().getId(),
                material.getTitle(),
                material.getMaterialUrl()
        );
    }
}
