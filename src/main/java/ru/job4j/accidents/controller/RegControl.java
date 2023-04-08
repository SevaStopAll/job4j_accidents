package ru.job4j.accidents.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepository;
import ru.job4j.accidents.repository.UserRepository;
import ru.job4j.accidents.service.SimpleUserService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final SimpleUserService users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, SimpleUserService users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(Model model, @ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        if (users.save(user)) {
            return "redirect:/login";
        }
        model.addAttribute("errorMessage", "Ошибка регистрации. Логин занят.");
        return "reg";
    }



    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}