package com.athome.springcloud.controller;

import com.athome.springcloud.entities.Contact;
import com.athome.springcloud.service.ContactService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class contactController {
    @Resource
    private ContactService contactService;

    @PostMapping("/save")
    @CrossOrigin
    public Contact save(@RequestBody Contact contact){
        return contactService.save(contact);
    }


    @GetMapping("/list")
    @CrossOrigin
    public List<Contact> list(){
        return contactService.list();
    }

    @GetMapping("/findById/{id}")
    @CrossOrigin
    public Contact findById(@PathVariable("id") Integer id){
        return contactService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @CrossOrigin
    public String deleteById(@PathVariable("id") Integer id){
        return contactService.deleteById(id);
    }

    @PostMapping("/upload")
    @CrossOrigin
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        return contactService.upload(file);

    }
}

