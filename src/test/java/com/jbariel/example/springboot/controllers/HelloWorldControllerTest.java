package com.jbariel.example.springboot.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/hello/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Hello World!")));
    }

    @Test
    public void shouldReturnNamedMessage() throws Exception {
        checkReturnMessageWithNamedParam("daniel");
        checkReturnMessageWithNamedParam("Jarr3tt");
//        checkReturnMessageWithNamedParam("#$%"); //encode decode special characters?
    }
    protected void checkReturnMessageWithNamedParam(String param) throws Exception {
        this.mockMvc.perform(get("/hello/" + URLEncoder.encode(param,"UTF-8"))).andDo(print()).andExpect(content().string(containsString("Hello " + param +"!"))).andExpect(status().isOk());
    }
}