package vn.iotstar.service;

import java.util.List;
import vn.iotstar.entity.User;

public interface IUserService {
    User login(String email, String password);
    boolean register(String email, String password, String fullname, Integer phone);
    boolean checkExistEmail(String email);
    boolean checkExistPhone(Integer phone);
    void update(User user);
    void delete(int id);
    List<User> findAll();
    User findById(int id);
    User findByUsername(String username);
    void insert(User user);
    User findByEmail(String email);
    User findByPhone(int phone);
    boolean updatePwd(User acc, boolean pwd);
}
