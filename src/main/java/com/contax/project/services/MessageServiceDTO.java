package com.contax.project.services;

import com.contax.project.dto.MessageDTO;
import com.contax.project.entities.Message;
import com.contax.project.exception.NotFoundException;
import com.contax.project.repositories.MessageRepository;
import com.contax.project.services.interfaces.IService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceDTO implements IService<MessageDTO> {

    private final MessageRepository repository;
    private final ModelMapper modelMapper;

    public MessageServiceDTO(MessageRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MessageDTO> findAll() {
        List<Message> messages = repository.findAll();
        return messages.stream()
                .map(message -> modelMapper.map(message, MessageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO findById(Long id) {
        Optional<Message> optionalMessage = repository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            return modelMapper.map(message, MessageDTO.class);
        } else {
            throw new NotFoundException("Message Not Found");
        }
    }

    @Override
    public MessageDTO save(MessageDTO messageDto) {
        Message message = modelMapper.map(messageDto, Message.class);
        message.setCreationDate(LocalDateTime.now());
        message.setUpdateDate(LocalDateTime.now());
        Message savedMessage = repository.save(message);
        return modelMapper.map(savedMessage, MessageDTO.class);
    }

    @Override
    public MessageDTO update(MessageDTO messageDto, Long id) {
        Optional<Message> optionalMessage = repository.findById(id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setContent(messageDto.getContent());
            message.setTitle(messageDto.getTitle());
            message.setUpdateDate(LocalDateTime.now());
            Message updatedMessage = repository.save(message);
            return modelMapper.map(updatedMessage, MessageDTO.class);
        } else {
            throw new NotFoundException("Message Not Found");
        }
    }

    @Override
    public MessageDTO delete(Long id) {
        Message message = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Message Not Found"));
        repository.deleteById(id);
        return modelMapper.map(message, MessageDTO.class);
    }
}
