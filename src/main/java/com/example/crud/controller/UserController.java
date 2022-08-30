package com.example.crud.controller;

import com.example.crud.dto.PageDTO;
import com.example.crud.dto.StatusDTO;
import com.example.crud.dto.UserDTO;
import com.example.crud.model.JwtResponse;
import com.example.crud.model.UserModel;
import com.example.crud.service.UserService;
import com.example.crud.serviceImpl.CustomUserDetailsService;
import com.example.crud.transformer.UserTransformer;
import com.example.crud.util.JwtUtil;
import com.example.crud.util.PaginationUtil;
import com.example.crud.util.Utility;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/createUser")
    public ResponseEntity<StatusDTO> saveUser(@RequestBody UserDTO userDTO){
        try {
            UserModel userModel = UserTransformer.getModelFromDTO(userDTO);
            userModel.setStatus(true);
            UserModel userModel1 = userService.saveUser(userModel);
            return new ResponseEntity<>(new StatusDTO(1,"User Created Successfully ",UserTransformer.getDTOFromModel(userModel1)), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + exception), HttpStatus.OK);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<StatusDTO> updateUser(@RequestBody UserDTO userDTO){
        try {
            UserModel byId = this.userService.findById(Integer.parseInt(userDTO.getUserId()));
            if(byId == null){
                return new ResponseEntity<>(new StatusDTO(0,"UserId Not Found"),HttpStatus.NOT_FOUND);
            }
            UserModel modelFromDTO = UserTransformer.getModelFromDTO(userDTO);
            modelFromDTO.setStatus(true);
            UserModel userModel = userService.updateUser(modelFromDTO);
            return new ResponseEntity<>(new StatusDTO(1,"User Updated Successfully",UserTransformer.getDTOFromModel(userModel)),HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + exception), HttpStatus.OK);
        }
    }

    @GetMapping(value = "/view/{id}", produces = "application/json")
    public ResponseEntity<StatusDTO> viewUserById(@PathVariable("id") Integer id) {
        UserModel userModel;
        UserDTO userDTO = null;
        try {
            userModel = userService.findById(id);
            if (userModel != null) {
                userDTO = UserTransformer.getDTOFromModel(userModel);
                return new ResponseEntity<>(new StatusDTO(1, "User Found Successfully", userDTO), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new StatusDTO(0, "User Id Not Found "), HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + exception), HttpStatus.OK);
        }
    }
    @PutMapping("/deleteUser/{id}")
    public ResponseEntity<StatusDTO> deleteUser(@PathVariable("id") Integer id){
        try {
            UserModel userModel = userService.findById(id);
            if(userModel == null){
                return new ResponseEntity<>(new StatusDTO(0,"User Id Not Found"), HttpStatus.NOT_FOUND);
            }else{
                userService.deleteUser(id);
                return new ResponseEntity<>(new StatusDTO(1,"User Delete Successfully"), HttpStatus.OK);
            }
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + exception), HttpStatus.OK);
        }
    }
    //creating Post mapping that get the list of departments from the database
    @PostMapping("/views")
    public PageDTO getAll(@ModelAttribute PaginationUtil paginationUtil) {
        Map<String, String> params = new HashMap<>();
        System.out.println(paginationUtil.toString());
        params.put("currentPage", paginationUtil.getCurrentPage().toString());
        params.put("itemsPerPage", paginationUtil.getItemsPerPage().toString());
        params.put("sortBy", paginationUtil.getSortBy());
        params.put("direction", paginationUtil.getDirection());
        Page<UserModel> page = userService.findAllByFilterWithPaging(Utility.createPageRequest(params));
        return new PageDTO(UserTransformer.getListDTOS(page.getContent()), page.getTotalElements(), page.getTotalPages());
    }
    @GetMapping("/viewss")
    public ResponseEntity<?> getAll() {
        try {
            List<UserModel> list = userService.listOfUser();
              return new ResponseEntity<>(new StatusDTO(0, "List of Users ",list), HttpStatus.OK);

        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! " + exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UserModel userModel){
        try{
            UserModel userModel1 = this.userService.loginUser(userModel.getUserName(), userModel.getUserPassword());
            if(userModel1 == null){
                return new ResponseEntity<>(new StatusDTO(0,"This user is deleted. You can't generate token with this username and password"),HttpStatus.NOT_FOUND);
            }else if(!userModel1.isStatus()){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }else
                this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getUserName(), userModel.getUserPassword()));
        }catch(Exception exception){
            exception.printStackTrace();
        }
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(userModel.getUserName());
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT : " + token);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

