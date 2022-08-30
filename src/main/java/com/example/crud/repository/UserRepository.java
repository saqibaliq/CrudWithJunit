package com.example.crud.repository;

import com.example.crud.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer>, JpaSpecificationExecutor<UserModel> {

    Page<UserModel> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update user set status = false where user_id = :user_id",nativeQuery = true)
    void deleteUser(@Param("user_id") Integer user_id);

    UserModel findByUserNameAndUserPassword(String user_name,String user_password);

    UserModel findByUserName(String user_name) throws Exception;
}
