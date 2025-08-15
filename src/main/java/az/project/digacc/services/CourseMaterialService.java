package az.project.digacc.services;

import az.project.digacc.dto.CourseMaterialDto;

import java.util.List;

public interface CourseMaterialService {
    List<CourseMaterialDto> getAllMaterials();
    List<CourseMaterialDto> getMaterialsByVideoId(Long videoId);
    CourseMaterialDto getMaterialById(Long id);
    CourseMaterialDto createMaterial(CourseMaterialDto materialDto);
    CourseMaterialDto updateMaterial(Long id, CourseMaterialDto materialDto);
    void deleteMaterial(Long id);
}
