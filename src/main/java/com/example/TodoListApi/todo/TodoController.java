package com.example.TodoListApi.todo;

import com.example.TodoListApi.user.UserModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
@Log4j2
public class TodoController {



    @Autowired
    TodoService todoService;



    @GetMapping("/get")
    public List<Todo> getAllUserTodos(@AuthenticationPrincipal UserModel userModel){
        System.out.println(todoService.getAllUserTodos(userModel.getId()));
        return   todoService.getAllUserTodos(userModel.getId());
    }

    @PostMapping("/create")
    public Todo createUserTodo(@RequestBody TodoModel todoModel, @AuthenticationPrincipal UserModel userModel){

        return todoService.createTodo(todoModel, userModel.getId());

    }

    @PutMapping("/update")
    public Todo updateUserTodo(@RequestBody TodoModel todoModel, @AuthenticationPrincipal UserModel userModel){
        System.out.println(todoModel);
        return todoService.updateTodo(userModel.getId(), todoModel.getTodoId(), todoModel.getTodoNote(), todoModel.isChecked());


    }

    @PostMapping("/delete")
    public ResponseEntity deleteUserTodo(@RequestBody TodoModel todoModel, @AuthenticationPrincipal UserModel userModel){
        log.info(todoModel);
        System.out.println("TODO MODEL LOG: "+todoModel);
        boolean val = todoService.deleteTodo(userModel.getId(), todoModel.getTodoId());

        if (!val)
            return ResponseEntity.badRequest().body("The Todo object could not be deleted!");

        return ResponseEntity.ok(200);


    }







}

