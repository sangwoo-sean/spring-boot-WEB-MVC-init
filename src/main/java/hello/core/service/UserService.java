package hello.core.service;


import hello.model.User;

import javax.transaction.TransactionRolledbackException;

public interface UserService {

    void join(User user) throws TransactionRolledbackException;

    User findUserById(Long userId);

    User findUserByName(String userName);
}
