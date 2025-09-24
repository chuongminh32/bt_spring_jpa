package vn.iotstar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.entity.User;
import vn.iotstar.repository.UserRepository;
import vn.iotstar.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> password.equals(u.getPassword()))
                .orElse(null);
    }

    @Override
    public boolean register(String email, String password, String fullname, Integer phone) {
        if (checkExistEmail(email) || checkExistPhone(phone)) {
            return false;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // TODO: nên hash password
        user.setFullName(fullname);
        user.setPhone(phone);
        user.setIsAdmin(false);

        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public boolean checkExistPhone(Integer phone) {
        return userRepository.findByPhone(phone).isPresent();
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findByPhone(int phone) {
        return userRepository.findByPhone(phone).orElse(null);
    }

    @Override
    public boolean updatePwd(User acc, boolean pwd) {
        // Giả sử boolean pwd nghĩa là active/inactive
        acc.setIsAdmin(pwd); // HOẶC sửa field phù hợp
        userRepository.save(acc);
        return true;
    }

}
