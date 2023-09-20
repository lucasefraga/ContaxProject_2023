package com.contax.project.controllers;

import com.contax.project.config.ErrorMessage;
import com.contax.project.dto.MessageDTO;
import com.contax.project.exception.NotFoundException;
import com.contax.project.services.MessageServiceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messagesDto")
public class MessageDTOController {

    private final MessageServiceDTO service;

    public MessageDTOController(MessageServiceDTO service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<MessageDTO>> findAll() {
        List<MessageDTO> messages = service.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MessageDTO> save(@Validated @RequestBody MessageDTO messageDto) {
        MessageDTO message = service.save(messageDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            MessageDTO message = service.findById(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(new ErrorMessage("No message was found with the ID: " + id), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody MessageDTO messageDto, @PathVariable Long id) {
        try {
            MessageDTO message = service.update(messageDto, id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(new ErrorMessage("No message was found with the ID: " + id), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            MessageDTO message = service.delete(id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(new ErrorMessage("No message was found with the ID: " + id), HttpStatus.NOT_FOUND);
        }
    }
}
