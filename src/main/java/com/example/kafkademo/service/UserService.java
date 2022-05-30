package com.example.kafkademo.service;

import com.example.kafkademo.entity.User;
import com.example.kafkademo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional(propagation = Propagation.REQUIRED)
    public User createUser(User user) {
        User userCreated = userRepository.save(user);
        sendMessage(String.format("User with id %s was created!", userCreated.getId()));
        return userCreated;
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void sendMessage(String msg) {
        kafkaTemplate.send("test-topic", msg);
    }
}
