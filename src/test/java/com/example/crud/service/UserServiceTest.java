package com.example.crud.service;

import com.example.crud.model.UserModel;
import com.example.crud.repository.UserRepository;
import com.example.crud.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class UserServiceTest {


    @Test
    public void createUser(){
        UserModel userModel = new UserModel();
        userModel.setUserId(22);
        userModel.setUserName("saqib");
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.save(userModel)).thenReturn(userModel);
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        UserModel userModel1 = userService.saveUser(userModel);
        assertEquals(userModel1.getUserName(),"ahmed");
    }
    @Test
    public void findUserById(){
        UserModel userModel = new UserModel();
        userModel.setUserId(22);
        userModel.setUserName("saqib");
        UserRepository mock = Mockito.mock(UserRepository.class);
        when(mock.findById(22)).thenReturn(Optional.of(userModel));
        UserServiceImpl userService = new UserServiceImpl(mock);
        UserModel byId = userService.findById(22);
        assertEquals(22,byId.getUserId());
    }

    @Test
    public void deleteUser(){
        UserModel userModel = new UserModel();
        userModel.setUserId(22);
        //userModel.setUserName("saqib");
        //Here I am Testing User Id Should Not be Null
        assertNotNull(userModel.getUserId(),"User Id Is Null");
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findById(userModel.getUserId())).thenReturn(Optional.of(userModel));
        userRepository.deleteUser(userModel.getUserId());
        verify(userRepository).deleteUser(userModel.getUserId());
    }
}