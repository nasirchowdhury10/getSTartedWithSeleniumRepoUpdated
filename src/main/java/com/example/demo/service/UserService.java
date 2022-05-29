package com.example.demo.service;

import com.example.demo.exception.InvalidArgmentException;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private  final List<UserDTO> userDTOList;

    public  UserService(){
        this.userDTOList = new ArrayList<>();

        userDTOList.add(new UserDTO("Nsir uddin", 21,1));
        userDTOList.add(new UserDTO("Shagor", 18,2));
    }


    public List<UserDTO> getUsers(){

        return userDTOList;
    }

    public UserDTO getUserById(long id) throws RecordNotFoundException{
        if (id < 1)
        throw new InvalidArgmentException("Invalid user  id");

        for (UserDTO dto: userDTOList){
            if(dto.getStudentid() == id){
                return dto;
            }
        }

        throw new RecordNotFoundException("users not  error");
    }





}
