package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserRepository;

@AllArgsConstructor
@Service
public class SimpleUserService {
    private final UserRepository userRepository;
    public boolean save(User user) {
        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
        return true;
    }

}
