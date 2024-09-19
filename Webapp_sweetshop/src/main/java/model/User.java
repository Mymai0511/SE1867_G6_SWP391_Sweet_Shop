package model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String fName;
    private boolean gender;
    private String email;
    private String phone;
    private Date dob;
    private String avatar;
    private String address;
    private int status;
    private Date createdAt;
    private Date updatedAt;
    private int role;

}