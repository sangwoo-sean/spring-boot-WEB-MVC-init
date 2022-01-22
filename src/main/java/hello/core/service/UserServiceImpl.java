package hello.core.service;

import hello.core.repository.UserRepository;
import hello.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionRolledbackException;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public void join(User user) throws TransactionRolledbackException {
        userRepository.save(user);

        if (user.getName().equals("error")) {
            throw new TransactionRolledbackException(); // for rollback test
        }
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User findUserByName(String userName) {
        return userRepository.findByName(userName);
    }
}
