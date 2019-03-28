package pku.chaoqun.logindemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.DigestUtils;
import pku.chaoqun.logindemo.model.User;
import pku.chaoqun.logindemo.service.UserService;

import java.util.Random;

/**
 * @author wuchaoqun
 */
@SpringBootApplication
@EnableJpaRepositories
public class LoginDemoApplication implements ApplicationRunner {

    @Autowired
    private UserService service;

    public static void main(String[] args) {
        SpringApplication.run(LoginDemoApplication.class, args);
    }

    // init() 仅执行一次，给数据库插入（admin，123456）数据提供登录，第二次运行前请注释
    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    private void init(){
        String salt = salt(32);
        String pwd = DigestUtils.md5DigestAsHex("123456".getBytes());
        String pwdHash = DigestUtils.md5DigestAsHex((pwd + salt).getBytes());
        User user = User.builder()
                .username("admin")
                .pwdHash(pwdHash)
                .salt(salt)
                .build();
        service.saveUser(user);
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
