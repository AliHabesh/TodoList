package com.example.TodoListApi.user;
import com.example.TodoListApi.todo.Todo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;


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

    @OneToMany(mappedBy = "ownerId")
    private List<Todo> todos;


    public User(String username, String email, String password, String role, boolean isAccountActive, List<Todo> todos) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isAccountActive = isAccountActive;
        this.todos = todos;

    }
}

