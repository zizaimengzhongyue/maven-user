package zizaimengzhongyue.com.github;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private Integer uid;
    private String name;

    public User() {
    }

    public User(Integer id, Integer uid, String name) {
        this.id = id;
        this.uid = uid;
        this.name = name;
    }
}
