package com.example.crud.serviceImpl;

import com.example.crud.model.UserModel;
import com.example.crud.repository.UserRepository;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel saveUser(UserModel userBean) {
        return this.userRepository.save(userBean);
    }

    @Override
    public UserModel updateUser(UserModel userBean) {
        if(userBean.getUserId() != null){
            Optional<UserModel> persisted = this.userRepository.findById(userBean.getUserId());
            if(persisted == null){
                return null;
            }
            UserModel userModel = this.userRepository.save(userBean);
            return userModel;
        }
        return null;
    }

    @Override
    public void deleteUser(Integer userId)
    {
          this.userRepository.deleteUser(userId);
    }

    @Override
    public List<UserModel> listOfUser() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findById(Integer id) {
        Optional<UserModel> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public Page<UserModel> findAllByFilterWithPaging(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public UserModel loginUser(String username, String password) {
        try {
            return this.userRepository.findByUserNameAndUserPassword(username, password);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return null;
    }
}
