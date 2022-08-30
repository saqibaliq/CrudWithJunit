//package com.example.crud.repository;
//
//import com.example.crud.model.UserModel;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
////    @BeforeEach
////    void initUseCase(){
////    UserModel userModel = new UserModel();
////        List<UserModel> list = new ArrayList<>();
////        userModel.setUserName("example");
////        userModel.setUserPassword("abcdef");
////        userModel.setUserAge(20);
////        userModel.setUserEmail("saqib@gmail.com");
////        userModel.setStatus(true);
////        list.add(userModel);
////        userRepository.saveAll(list);
////    }
//    @AfterEach
//    public void destroyAll(){
//        userRepository.deleteAll();
//    }
//    @Test
//    void saveAll_success() {
//        UserModel userModel = new UserModel();
//        UserModel userModel1 = new UserModel();
//        UserModel userModel2 = new UserModel();
//        List<UserModel> list = new ArrayList<>();
//        userModel.setUserName("example");
//        userModel.setUserPassword("abcdef");
//        userModel.setUserAge(20);
//        userModel.setUserEmail("saqib@gmail.com");
//        userModel.setStatus(true);
//        userModel1.setUserName("example");
//        userModel1.setUserPassword("abcdef");
//        userModel1.setUserAge(20);
//        userModel1.setUserEmail("saqib@gmail.com");
//        userModel1.setStatus(true);
//        userModel2.setUserName("example");
//        userModel2.setUserPassword("abcdef");
//        userModel2.setUserAge(20);
//        userModel2.setUserEmail("saqib@gmail.com");
//        userModel2.setStatus(true);
//        list.add(userModel);
//        list.add(userModel1);
//        list.add(userModel2);
//        List<UserModel> list1 = userRepository.saveAll(list);
//
//        AtomicInteger validIdFound = new AtomicInteger();
//        list1.forEach(user -> {
//            if(user.getUserId()>0){
//                validIdFound.getAndIncrement();
//            }
//        });
//
//        assertThat(validIdFound.intValue()).isEqualTo(3);
//    }
//
//    @Test
//    void findAll_success() {
//        List<UserModel> allCustomer = userRepository.findAll();
//        assertThat(allCustomer.size()).isGreaterThanOrEqualTo(1);
//    }
//    @Test
//    void findByName(){
//        try {
//            UserModel example = userRepository.findByUserName("example55");
//            assertThat(example.getUserName()).isEqualToIgnoringCase("example55");
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
//}