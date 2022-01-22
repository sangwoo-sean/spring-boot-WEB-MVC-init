package hello.core.repository;

import hello.core.mapper.TestMapper;
import hello.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class SQLUserRepository implements UserRepository {

    private final TestMapper testMapper;

    @Autowired
    public SQLUserRepository(TestMapper testMapper) {
        this.testMapper = testMapper;
    }

    @Override
    public void save(User user) {
        testMapper.insertUser(user);
    }

    @Override
    public User findById(Long userId) {
        return testMapper.selectUserById(userId);
    }

    @Override
    public User findByName(String userName) {
        return testMapper.selectUserByName(userName);
    }
}
