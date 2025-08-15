package az.project.digacc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterialDto {
    private Long id;
    private Long videoId;
    private String title;
    private String materialUrl;
}
