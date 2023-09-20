package com.contax.project.services;

import com.contax.project.entities.Message;
import com.contax.project.exception.NotFoundException;
import com.contax.project.repositories.MessageRepository;
import com.contax.project.services.interfaces.IService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IService<Message> {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Message findById(Long id) {
        Optional<Message> optionalMessage = repository.findById(id);
        if (optionalMessage.isPresent())
            return optionalMessage.get();
        else throw new NotFoundException("Message Not Found");
    }

    @Override
    public Message save(Message message) {
        message.setCreationDate(LocalDateTime.now());
        message.setUpdateDate(LocalDateTime.now());
        return repository.save(message);
    }

    @Override
    public Message update(Message message, Long id) {
        Optional<Message> optionalMessage = repository.findById(id);
        if (optionalMessage.isPresent()){
            Message updatedMessage = optionalMessage.get();
            updatedMessage.setContent(message.getContent());
            updatedMessage.setUpdateDate(LocalDateTime.now());
            return repository.save(updatedMessage);
        }
        else
            throw new NotFoundException("Message Not Found");
    }

    @Override
    public Message delete(Long id) {
        Message message = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Message Not Found"));
        repository.deleteById(id);
        return message;
    }
}
