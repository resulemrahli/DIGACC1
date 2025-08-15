package az.project.digacc.services;

import az.project.digacc.dto.ContactFormDto;
import az.project.digacc.entity.ContactFormEntity;
import az.project.digacc.repository.ContactFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactFormServiceImpl implements ContactFormService {
    private final ContactFormRepository contactFormRepository;

    private final String UPLOAD_DIR = "uploads\\cv\\";

    @Override
    public List<ContactFormEntity> getAllContactForms() {
        return contactFormRepository.findAll();
    }

    @Override
    public ContactFormEntity getContactFormById(Long id) {
        return contactFormRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact form not found"));
    }

    @Override
    public void submitContactForm(ContactFormDto contactFormDto) throws IOException {
        String cvUrl = saveFile(contactFormDto.getCv());

        ContactFormEntity contactForm = ContactFormEntity.builder()
                .firstName(contactFormDto.getFirstName())
                .lastName(contactFormDto.getLastName())
                .email(contactFormDto.getEmail())
                .phoneNumber(contactFormDto.getPhoneNumber())
                .cvUrl(cvUrl)
                .submittedAt(LocalDateTime.now())
                .build();

        contactFormRepository.save(contactForm);
    }

    @Override
    public void deleteContactForm(Long id) {
        if (!contactFormRepository.existsById(id)) {
            throw new RuntimeException("Contact form not found");
        }
        contactFormRepository.deleteById(id);
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);

        Files.copy(file.getInputStream(), filePath);

        return UPLOAD_DIR + filename;
    }
}
