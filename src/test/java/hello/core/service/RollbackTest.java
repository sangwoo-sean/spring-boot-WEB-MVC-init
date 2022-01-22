package hello.core.service;

import hello.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RollbackTest {

    @Autowired UserService userService;

    @Test
    @DisplayName("정상로직 테스트")
    @Transactional
    void joinOk() {
        //given
        User userA = new User("userA");

        //when
        try {
            userService.join(userA);
        } catch (Exception e) {

        }

        //then
        User user = userService.findUserById(userA.getId());
        assertThat(user.getName()).isEqualTo("userA");
    }

    @Test
    @DisplayName("롤백 테스트")
    void rollback() {
        //given
        User userB = new User("error");

        //when
        Exception e = assertThrows(Exception.class, () -> {
            userService.join(userB);
        });
        User user = userService.findUserByName("error");

        //then
        assertThat(e.getClass().getSimpleName()).isEqualTo("TransactionRolledbackException");
        assertThat(user).isEqualTo(null);

        // description : user 이름이 error 인 경우, 생성 후에 exception 이 throw 된다.
        // 롤백이 X : error 이름의 user 가 생겨야한다.
        // 롤백이 O : error 이름의 user 가 없다.
    }
}
