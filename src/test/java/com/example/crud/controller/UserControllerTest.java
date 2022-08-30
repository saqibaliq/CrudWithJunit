package com.example.crud.controller;

import com.example.crud.dto.StatusDTO;
import com.example.crud.model.UserModel;
import com.example.crud.service.UserService;
import com.example.crud.transformer.UserTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    UserModel userModel;
    List<UserModel> list;
    @BeforeEach
    void initUseCase(){
        userModel = new UserModel();
        list = new ArrayList<>();
        userModel.setUserName("example");
        userModel.setUserPassword("abcdef");
        userModel.setUserAge(20);
        userModel.setUserEmail("saqib@gmail.com");
        userModel.setStatus(true);
        list.add(userModel);
    }
    @Test
    public void createUserTest() throws Exception {
        when(userService.saveUser(ArgumentMatchers.any())).thenReturn(new UserModel());
        UserModel userModel = new UserModel();
        userModel.setUserName("saqib");
        userModel.setUserEmail("saqib@gmail.com");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(userModel);
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.post("/user/createUser/").contentType(MediaType.APPLICATION_JSON).content(s);
        ResultActions perform = mockMvc.perform(content);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
    }
    @Test
    public void viewAllUser() throws Exception {
        when(userService.listOfUser()).thenReturn(list);
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.get("/user/viewss/");
        ResultActions perform = mockMvc.perform(content);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
    }

    @Test
    public void deleteUser() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setUserId(22);
        userModel.setUserName("saqib");
        when(userService.findById(22)).thenReturn((userModel));
        MockHttpServletRequestBuilder content = MockMvcRequestBuilders.put("/user/deleteUser/"+userModel.getUserId().toString());
        ResultActions perform = mockMvc.perform(content);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
    }
    @Test
    public void findUserById() throws Exception {
        UserModel userModel = new UserModel();
        userModel.setUserId(20);
        when(userService.findById(20)).thenReturn(userModel);
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/user/view/" + userModel.getUserId().toString());
        ResultActions perform = mockMvc.perform(mockHttpServletRequestBuilder);
        MvcResult mvcResult = perform.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
    }
}