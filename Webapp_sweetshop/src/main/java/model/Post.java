package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import java.util.Date;

public class Post {
    private int id;
    private String title;
    private String content;
    private int status; // 1: Công khai, 0: Nháp
    private Date createdAt;
    private Date updatedAt;
    private int userID;

    public Post() {}

    public Post(int id, String title, String content, int status, Date createdAt, Date updatedAt, int userID) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userID = userID;
    }

    // Getters và Setters
    // ...
}
