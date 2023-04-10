package ru.job4j.accidents.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnRegisterPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andExpect(view().name("reg"));
    }

    /*@Test
    @WithMockUser
    public void whenRegSave() throws Exception {
        this.mockMvc.perform(post("/reg")
                        .param("username", "Petr")
                        .param("password", "123"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getUsername()).isEqualTo("Petr");
    }*/
}