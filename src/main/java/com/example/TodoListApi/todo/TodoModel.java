package com.example.TodoListApi.todo;

import lombok.Data;

@Data
public class TodoModel {

    private Long todoId;
    private Long ownerId;
    private String todoNote;
    private boolean isChecked;

    public TodoModel() {
    }
}

