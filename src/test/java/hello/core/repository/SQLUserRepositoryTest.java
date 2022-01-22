package hello.core.repository;

import hello.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SQLUserRepositoryTest {

    @Autowired UserRepository userRepository;

    @Test
    @DisplayName("리포지토리 연결 테스트")
    void repoTest() {
        User tester = new User("tester");
        userRepository.save(tester);

        User user = userRepository.findById(tester.getId());
        assertThat(user.getName()).isEqualTo("tester");
    }
}
