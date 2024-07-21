package com.test.warehouse.service.user;

import com.test.warehouse.Exception.DataErrorException;
import com.test.warehouse.Util.Constants;
import com.test.warehouse.dto.request.user.RequestUserDTO;
import com.test.warehouse.dto.response.user.ResponseUserDTO;
import com.test.warehouse.entity.User;
import com.test.warehouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    //TODO error query user -> hipotesa karena user harus menggunakan petik dua ("), penulisan Postgresql

    @Autowired
    private UserRepository repository;

    @Override
    public List<ResponseUserDTO> getAllUser() {
        List<User> user = repository.findByDeleted(Boolean.FALSE);
        return user.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public ResponseUserDTO getByIdUser(long id) {
        return convert(getById(id));
    }

    @Override
    public ResponseUserDTO createUser(RequestUserDTO userDTO) {

        String userName = userDTO.getUserName();
        checkUserName(userName);

        User user = new User();
        user.setId(0);
        user.setUserName(userName);
        user.setRole(userDTO.getRole());
        user.setDeleted(Boolean.FALSE);
        user.setDateCreated(Constants.getTimestamp());
        return convert(repository.save(user));
    }

    @Override
    public ResponseUserDTO updateUser(long id, RequestUserDTO userDTO) {

        String userName = userDTO.getUserName();
        checkUserName(userName);

        User user = getById(id);
        user.setUserName(userName);
        user.setRole(userDTO.getRole());
        user.setDeleted(Boolean.FALSE);
        user.setDateModified(Constants.getTimestamp());
        return convert(repository.save(user));
    }

    @Override
    public void deleteUser(long id) {
        User user = getById(id);
        user.setDeleted(Boolean.TRUE);
        user.setDateModified(Constants.getTimestamp());
        repository.save(user);
    }

    private ResponseUserDTO convert(User user){
        ResponseUserDTO userDTO = new ResponseUserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    private User getById(long id){
        Optional<User> byId = repository.findById(id);
        if(!byId.isPresent())
            throw new DataErrorException("User not found");
        return byId.get();
    }

    private void checkUserName(String userName){
        if (repository.findByUserName(userName).isPresent())
            throw new DataErrorException("User Name is already exist");
    }
}
