package com.example.TodoListApi.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnore
    private Long ownerId;
    private String todoNote;
    private boolean isChecked;

    public Todo(Long ownerId, String todoNote, boolean isChecked) {
        this.ownerId = ownerId;
        this.todoNote = todoNote;
        this.isChecked = isChecked;
    }
}
