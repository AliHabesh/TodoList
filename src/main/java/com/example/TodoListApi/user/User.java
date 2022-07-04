package com.example.TodoListApi.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private String role;
    private boolean isAccountActive = false;



    public User(String username, String email, String password, String role, boolean isAccountActive) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAccountActive = isAccountActive;

    }
}

