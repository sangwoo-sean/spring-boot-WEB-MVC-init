package hello.core.repository;

import hello.model.User;

public interface UserRepository {

    void save(User user);

    User findById(Long userId);

    User findByName(String userName);
}

