package ru.eltex.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(new User (1, "Brown", "+71231232323"));
        users.add(new User (2, "Alex", "+78531239856"));
        users.add(new User (3, "Vega", "+79356782356"));
        System.out.println("\nTest output info");
        users.forEach(User::printInf);
        System.out.println();
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(users);

        this.mockMvc.perform(get("http://localhost:8081/get_users")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    public void getUser() throws Exception {
        User user = new User (1, "Brown", "+71231232323");
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(user);

        this.mockMvc.perform(get("http://localhost:8081/get_user?id=1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }

    @Test
    public void getUser1() throws Exception {
        User user = new User (1, "Brown", "+71231232323");
        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(user);

        this.mockMvc.perform(get("http://localhost:8081/get_user/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(expectedResult));
    }
}