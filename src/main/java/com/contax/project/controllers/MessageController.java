package com.contax.project.controllers;

import com.contax.project.config.ErrorMessage;
import com.contax.project.entities.Message;
import com.contax.project.exception.NotFoundException;
import com.contax.project.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }


    @GetMapping()
    public ResponseEntity<List<Message>> findAll(){
        List<Message> messages = service.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> save(@Validated @RequestBody Message messageSent){
        Message message = service.save(messageSent);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            Message message = service.findById(id);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }catch (NotFoundException ex){
            return new ResponseEntity<>(new ErrorMessage("No message was found with the ID: " + id),HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Message messageSent, @PathVariable Long id){
        try {
            Message message = service.update(messageSent, id);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }catch (NotFoundException ex){
            return new ResponseEntity<>(new ErrorMessage("No message was found with the ID: " + id),HttpStatus.NOT_FOUND);
        }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            Message message = service.delete(id);
            return new ResponseEntity<>(message,HttpStatus.OK);
        }catch (NotFoundException ex){
            return new ResponseEntity<>(new ErrorMessage("No message was found with the ID: " + id),HttpStatus.NOT_FOUND);
        }
    }

}
