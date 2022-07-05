package com.example.TodoListApi.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {


    @Autowired
    TodoRepository todoRepository;

    public List<Todo> getAllUserTodos(Long id){

        return todoRepository.findAllByOwnerId(id);


    }


    public Todo getUserTodo(Long todoId, Long ownerId){
        return todoRepository.findByIdAndOwnerId(todoId, ownerId);
    }

    public boolean deleteTodo(Long ownerId, Long todoId){
        return todoRepository.deleteByOwnerIdAndId(ownerId, todoId);
    }

    public Todo updateTodo(Long ownerId, Long todoId, String todoNote, boolean check){
        Todo todo = getUserTodo(todoId, ownerId);

        if (todoNote != null) todo.setTodoNote(todoNote);

        if (todo.isChecked() != check) todo.setChecked(check);

        return todoRepository.save(todo);
    }

    public Todo createTodo(TodoModel todoModel, Long id){

        if (todoModel == null || id == null || todoModel.getTodoNote() == null){
            return null;
        }



        Todo todo = new Todo();
        todo.setTodoNote(todoModel.getTodoNote());
        todo.setChecked(false);
        todo.setOwnerId(id);

        return todoRepository.save(todo);
    }



}