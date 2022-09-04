package com.example.kafkademo.service;

import com.example.kafkademo.entity.User;
import com.example.kafkademo.repository.UserRepository;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        String userName = "testuser";
        String password = "testpassword";
        String url = "jdbc:mysql://localhost:3306/testdatabase";
        try (Connection conn = DriverManager.getConnection(url, userName, password)) {
            DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
            Result<Record> result = create.select().from("t_user").where().fetch();

            for (Record r : result) {
                Long user_id = (Long) r.getValue("id");
                String user_username = (String) r.getValue("username");
                String user_password = (String) r.getValue("password");

                System.out.println("ID: " + user_id + " username: " + user_username + " password: " + user_password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private void sendMessage(String msg) {
        kafkaTemplate.send("test-topic", msg);
    }
}
