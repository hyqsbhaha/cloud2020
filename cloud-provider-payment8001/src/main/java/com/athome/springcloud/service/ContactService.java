package com.athome.springcloud.service;

import com.athome.springcloud.entities.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContactService {
    Contact save(Contact contact);

    List<Contact> list();

    Contact findById(Integer id);

    String deleteById(Integer id);

    String upload(MultipartFile file) throws IOException;
}
