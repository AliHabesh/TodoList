package com.example.TodoListApi.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Todo findByOwnerId(Long ownerId);

    Todo findByIdAndOwnerId(Long id, Long ownerId);

    List<Todo> findAllByOwnerId(Long id);

    @Transactional
    boolean deleteByOwnerIdAndId(Long ownerId, Long id);

}
