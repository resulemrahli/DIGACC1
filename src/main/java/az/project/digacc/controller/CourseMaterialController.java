package az.project.digacc.controller;

import az.project.digacc.dto.CourseMaterialDto;
import az.project.digacc.services.CourseMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @GetMapping
    public ResponseEntity<List<CourseMaterialDto>> getAllMaterials() {
        return ResponseEntity.ok(courseMaterialService.getAllMaterials());
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<CourseMaterialDto>> getMaterialsByVideoId(@PathVariable Long videoId) {
        return ResponseEntity.ok(courseMaterialService.getMaterialsByVideoId(videoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseMaterialDto> getMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok(courseMaterialService.getMaterialById(id));
    }

    @PostMapping
    public ResponseEntity<CourseMaterialDto> createMaterial(@RequestBody CourseMaterialDto materialDto) {
        return new ResponseEntity<>(courseMaterialService.createMaterial(materialDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseMaterialDto> updateMaterial(@PathVariable Long id, @RequestBody CourseMaterialDto materialDto) {
        return ResponseEntity.ok(courseMaterialService.updateMaterial(id, materialDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        courseMaterialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}