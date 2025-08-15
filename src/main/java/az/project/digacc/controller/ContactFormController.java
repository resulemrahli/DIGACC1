package az.project.digacc.controller;

import az.project.digacc.dto.ContactFormDto;
import az.project.digacc.entity.ContactFormEntity;
import az.project.digacc.services.ContactFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/contact-forms")
@RequiredArgsConstructor
public class ContactFormController {

    private final ContactFormService contactFormService;

    @GetMapping
    public ResponseEntity<List<ContactFormEntity>> getAllContactForms() {
        return ResponseEntity.ok(contactFormService.getAllContactForms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactFormEntity> getContactFormById(@PathVariable Long id) {
        return ResponseEntity.ok(contactFormService.getContactFormById(id));
    }

    @PostMapping
    public ResponseEntity<Void> submitContactForm(@ModelAttribute ContactFormDto contactFormDto) throws IOException {
        contactFormService.submitContactForm(contactFormDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactForm(@PathVariable Long id) {
        contactFormService.deleteContactForm(id);
        return ResponseEntity.noContent().build();
    }
}
