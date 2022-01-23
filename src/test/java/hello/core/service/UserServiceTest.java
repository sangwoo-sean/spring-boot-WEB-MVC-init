package hello.core.service;

import hello.core.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionRolledbackException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    @DisplayName("MySql연동 테스트")
    void DBConnection() { // User db table has to have Columns : id, name
        //given
        User joinUser = new User("tester");
        try {
            userService.join(joinUser);
        } catch (TransactionRolledbackException e) {
            e.printStackTrace();
        }

        //when
        User user = userService.findUserById(joinUser.getId());

        //then
        assertThat(user.getName()).isEqualTo("tester");
    }

    @Test
    @DisplayName("유저 회원가입 테스트")
    void userJoin() {
        //given
        User user = new User("userA");

        //when
        try {
            userService.join(user);
        }
        catch (Exception e) {

        }
        User joinUser = userService.findUserById(user.getId());

        //then
        assertThat(joinUser.getName()).isEqualTo("userA");
    }
}
