package com.spring.web.springbootfirstwebapplication.service;

import com.spring.web.springbootfirstwebapplication.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "in28Minutes", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "in28Minutes", "Learn Struts", new Date(),
                false));
        todos.add(new Todo(3, "Shashank", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addTodo(String name, String desc, Date targetDate,
                        boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }

    public Todo retrieveTodoforUpdate(int id){

        Iterator<Todo> iterator = todos.iterator();
        while(iterator.hasNext()){
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    public void updateTodos(String user,int id, String desc, boolean isDone,Date date) {

        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user) && todo.getId()== id) {
                todo.setTargetDate(date);
                todo.setDesc(desc);
                todo.setDone(isDone);
            }
        }
    }

}
