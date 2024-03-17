package com.mySpringboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java. util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {

	private TodoService todoService;
	
	
	public TodoController(TodoService todoService) 
	{
		super();
		this.todoService = todoService;
	}


	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model)
	{
		String username = getLoggedinUsername(model);
		List<Todo> todos = todoService.findByUsername(username);
		model.addAttribute("todos", todos);  // with model we will have todos variable available in JSP
		return "ListTodos";
		
	}

	
	//add new todo
	@RequestMapping(value ="add-todo" ,method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model)
	{
		String username = getLoggedinUsername(model);
		Todo todo = new Todo(1,username,"",LocalDate.now().plusYears(1),false );
		model.put("todo", todo);
		return "todo";
		
	}
	
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 return authentication.getName();
		
	}


	//display all todo
	@RequestMapping(value ="add-todo" ,method = RequestMethod.POST)
	public String addNewTodo(ModelMap model,@Valid Todo todo ,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		
		String username = getLoggedinUsername(model);
		todoService.addTodo(username,todo.getDescription(),todo.getTargetDate(), false);
		return "redirect:list-todos";
		
	}
	
	//delete  todo with given id
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id)
	{
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	//update  todo with given id
	@RequestMapping(value="update-todo" ,method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model)
	{
		Todo todo = todoService.findByID(id);
		model.addAttribute("todo",todo);
		return "todo";
	}
	
	
	//display all todo
	@RequestMapping(value ="update-todo" ,method = RequestMethod.POST)
	public String updateTodo(ModelMap model,@Valid Todo todo ,BindingResult result)
	{
		if(result.hasErrors())
		{
			return "todo";
		}
		
		String username = getLoggedinUsername(model);
		todo.setUsername(username);
		todoService.updateTodo(todo);
		return "redirect:list-todos";
		
	}
}
