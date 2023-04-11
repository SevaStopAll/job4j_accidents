package ru.job4j.accidents.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.SimpleUserService;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControlTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimpleUserService users;

    @Test
    @WithMockUser
    public void shouldReturnRegisterPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void whenRegSave() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "Vsevolod")
                        .param("password", "password"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(users).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getUsername()).isEqualTo("Vsevolod");
    }
}