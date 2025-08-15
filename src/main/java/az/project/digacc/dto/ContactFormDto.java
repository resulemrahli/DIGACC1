package az.project.digacc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactFormDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private MultipartFile cv;
}
