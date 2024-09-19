package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private int id;
    private String title;
    private String content;
    private int status;
    private Date createdAt;
    private Date updatedAt;
}
