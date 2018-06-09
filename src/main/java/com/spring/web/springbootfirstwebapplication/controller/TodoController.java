package com.spring.web.springbootfirstwebapplication.controller;


import com.spring.web.springbootfirstwebapplication.model.Todo;
import com.spring.web.springbootfirstwebapplication.service.TodoService;
import org.eclipse.jdt.internal.compiler.lookup.SourceTypeBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(simpleDateFormat,false));
    }

    @RequestMapping(value="/show-todo", method = RequestMethod.GET)
    public String showTodoList(ModelMap model){
        model.put("todos",todoService.retrieveTodos(getLoggedinUserName(model)));
        return "show-todo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String addtodo(ModelMap model){
        model.addAttribute("todo", new Todo(0, getLoggedinUserName(model), " ",
                new Date(), false));
        return "add-todo";
    }

    private String getLoggedinUserName(ModelMap model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            return ((UserDetails)principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addtotodo(ModelMap model, @Valid Todo todo, BindingResult result){

        if(result.hasErrors())
            return "add-todo";

        todoService.addTodo(getLoggedinUserName(model), todo.getDesc(), todo.getTargetDate(),
                false);
        return "redirect:/show-todo";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.GET)
    public String updatetodo(@RequestParam int id,ModelMap model){
        model.addAttribute("todo",todoService.retrieveTodoforUpdate(id));
        return "update-todo";
    }

    @RequestMapping(value="/update-todo", method = RequestMethod.POST)
    public String updatetotodo(ModelMap model, @Valid Todo todo, BindingResult result){

        if(result.hasErrors())
            return "update-todo";

        todoService.updateTodos(getLoggedinUserName(model),todo.getId(), todo.getDesc(),
                false,todo.getTargetDate());
        return "redirect:/show-todo";
    }

    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
    public String deletetodo(@RequestParam int id){
        todoService.deleteTodo(id);
        return "redirect:/show-todo";
    }
}
