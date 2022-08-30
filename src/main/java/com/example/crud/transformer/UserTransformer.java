package com.example.crud.transformer;

import com.example.crud.dto.UserDTO;
import com.example.crud.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {
    public static UserModel getModelFromDTO(UserDTO userDTO){
        UserModel userModel = new UserModel();
        if(userDTO.getUserId() != null && !userDTO.getUserId().equals("")){
            userModel.setUserId(Integer.parseInt(userDTO.getUserId()));
        }
        if(userDTO.getUserName() != null && !userDTO.getUserName().equals("")){
            userModel.setUserName(userDTO.getUserName());
        }
        if(userDTO.getUserPassword() != null && !userDTO.getUserPassword().equals("")){
            userModel.setUserPassword(userDTO.getUserPassword());
        }
        if(userDTO.getUserEmail() != null && !userDTO.getUserEmail().equals("")){
            userModel.setUserEmail(userDTO.getUserEmail());
        }
        if(userDTO.getUserAge() != null && !userDTO.getUserAge().equals("")){
            userModel.setUserAge(Integer.parseInt(userDTO.getUserAge()));
        }

        return userModel;
    }
    public static UserDTO getDTOFromModel(UserModel userModel){
        UserDTO userDTO = new UserDTO();
        if(userModel.getUserId() != null && !userModel.getUserId().equals("")){
            userDTO.setUserId(userModel.getUserId().toString());
        }
        if(userModel.getUserName() != null && !userModel.getUserName().equals("")){
            userDTO.setUserName(userModel.getUserName());
        }
        if(userModel.getUserPassword() != null && !userModel.getUserPassword().equals("")){
            userDTO.setUserPassword(userModel.getUserPassword());
        }
        if(userModel.getUserEmail() != null && !userModel.getUserEmail().equals("")){
            userDTO.setUserEmail(userModel.getUserEmail());
        }
        if(userModel.getUserAge() != null && !userModel.getUserAge().equals("")){
            userDTO.setUserAge(userModel.getUserAge().toString());
        }
        return userDTO;
    }

    public static List<UserDTO> getListDTOS(List<UserModel> userModel){
        List<UserDTO> userDTOS = new ArrayList<>();
        for(UserModel userModel1 : userModel){
            UserDTO userDTO = UserTransformer.getDTOFromModel(userModel1);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }
}
