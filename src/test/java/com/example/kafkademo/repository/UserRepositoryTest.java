package com.example.kafkademo.repository;

import com.example.kafkademo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("h2")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    private void clearRepository() {
        userRepository.deleteAll();
    }

    @Test
    @Transactional
    @Rollback(false)
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        userRepository.save(new User("Zoorlos", "32189849"));
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void whenCalledSave_thenCorrectNumberOfUsers_1() {
        userRepository.save(new User("KostasD21", "xxx432423"));
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(1);
    }
}
