package hello.core.model;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String name;
    private String phone;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
