package pku.chaoqun.logindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pku.chaoqun.logindemo.model.User;
import pku.chaoqun.logindemo.service.UserService;

import java.util.Random;

/**
 * @author wuchaoqun
 */
@RestController
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/login")
    private String userLogin(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPwdHash();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "username or password is Empty!";
        }
        User retUser = service.getUserByName(username);
        String passwordMd5 = md5(password, retUser.getSalt());
        if (! passwordMd5.equals(retUser.getPwdHash())) {
            return "username or password wrong!";
        }
        return "login sucessful!";
    }

    @RequestMapping("/register")
    private String userRegister(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPwdHash();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "username or password is Empty!";
        }
        User retUser = service.getUserByName(username);
        if (retUser != null) {
            return "username is exit!";
        }
        String salt = salt(32);
        String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        String pwdHash = DigestUtils.md5DigestAsHex((pwd + salt).getBytes());
        User newUser = User.builder()
                .username(username)
                .pwdHash(pwdHash)
                .salt(salt)
                .build();
        service.saveUser(newUser);
        return "register sucessful!";
    }

    private static String md5(String password, String salt) {
        String newPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        return DigestUtils.md5DigestAsHex((newPassword + salt).getBytes());
    }

    private static String salt(int n) {
        Random random = new Random();
        String chars = "qwertyuiopasdfghjklzxcvbnm";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }

}
