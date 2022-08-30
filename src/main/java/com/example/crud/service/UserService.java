package com.example.crud.service;

import com.example.crud.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserModel saveUser(UserModel userBean);

    UserModel updateUser(UserModel userBean);

    void deleteUser(Integer userId);

    List<UserModel> listOfUser();

    UserModel findById(Integer id);

    Page<UserModel> findAllByFilterWithPaging(Pageable pageable);

    //login
    UserModel loginUser(String username, String password);
}
