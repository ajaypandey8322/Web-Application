  package com.mySpringboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int todosCount =0;
	
	
	static {
		todos.add(new Todo(++todosCount,"Ajay", "AWS*", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount,"Ajay", "gcp*", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount,"Ajay", "Devops*", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount,"Ajay", "Azure*", LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predeicate= todo -> todo.getUsername().equalsIgnoreCase(username);// functional programming using lamda  exp
		
		return todos.stream().filter(predeicate).toList();
	}
	
	public void addTodo(String username, String description , LocalDate targetDate , boolean isDone)
	{	
		Todo todo = new Todo(++todosCount, username, description , targetDate , isDone);
		todos.add(todo);
	}
	
	public void deleteById(int id)
	{
		
		Predicate<? super Todo> predeicate= todo -> todo.getId()==id;// functional programming using lamda  exp
		
		todos.removeIf(predeicate);
	}

	public Todo findByID(int id) 
	{
		Predicate<? super Todo> predeicate= todo -> todo.getId()==id;// functional programming using lamda  exp
		
		Todo todo = todos.stream().filter(predeicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo)
	{
		deleteById(todo.getId());
		todos.add(todo);
		
	}
}
