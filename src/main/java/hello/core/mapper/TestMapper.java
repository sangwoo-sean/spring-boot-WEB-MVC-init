package hello.core.mapper;

import hello.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface TestMapper {
    User selectUserById(@Param("id") Long userId);

    void insertUser(User user);

    User selectUserByName(@Param("name") String userName);
}
