package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.RuleService;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidents;
    private final RuleService rules;
    private final AccidentTypeService accidentsTypes;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentsTypes.findAll());
        model.addAttribute("rules", rules.findAll());
        return "accidents/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidents.create(accident, id, ids);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String formEdit(Model model, @RequestParam("id") int id) {
        var accidentOptional = accidents.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Такого происшествия не зарегистрировано");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        return "/accidents/editAccident";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidents.update(accident);
        return "redirect:/index";
    }
}
