package az.project.digacc.services;

import az.project.digacc.dto.ContactFormDto;
import az.project.digacc.entity.ContactFormEntity;

import java.io.IOException;
import java.util.List;

public interface ContactFormService {
    List<ContactFormEntity> getAllContactForms();
    ContactFormEntity getContactFormById(Long id);
    void submitContactForm(ContactFormDto contactFormDto) throws IOException;
    void deleteContactForm(Long id);
}
