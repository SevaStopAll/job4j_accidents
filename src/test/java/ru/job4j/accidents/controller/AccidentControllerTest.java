package ru.job4j.accidents.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.SimpleAccidentService;

@Transactional
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @MockBean
    private SimpleAccidentService accidents;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnCreatePage() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accidents/createAccident"));
    }

    @Test
    @WithMockUser
    public void whenEditAccidentAndError() throws Exception {
        this.mockMvc.perform(get("/editAccident/?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("errors/404"));
    }

    @Test
    @WithMockUser
    public void whenEditAccident() throws Exception {
        this.mockMvc.perform(get("/editAccident/?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/accidents/editAccident"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(post("/post/create")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> accident = ArgumentCaptor.forClass(Accident.class);
        verify(accidents).create(accident.capture());
        /*assertThat(accident.getValue().getName(), is("Куплю ладу-грант. Дорого."));*/
    }
}