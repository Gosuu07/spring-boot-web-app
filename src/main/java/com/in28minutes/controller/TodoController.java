package com.in28minutes.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.model.Todo;
import com.in28minutes.service.TodoService;

@Controller
@SessionAttributes("name2")
public class TodoController {

	@Autowired
	TodoService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
		
	}
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}
	private String getLoggedInUserName(ModelMap model) {
		return (String) model.get("name2");
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String showAddTodosPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "Default Desc", new Date(), false));
		return "todo";
	}

	@RequestMapping(value = "/delete-todos", method = RequestMethod.GET)
	public String deleteTodos(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value = "/update-todos", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);
		return "todo";
	}
	@RequestMapping(value = "/update-todos", method = RequestMethod.POST)
	public String updateTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
		

		if(result.hasErrors()) {
//			model.put("errorMessage","Desc  should be atleast 10 chars");
			return "todo";
		}
		todo.setUser(getLoggedInUserName(model));
		
		service.updateTodo(todo);
		
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String AddTodos(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()) {
//			model.put("errorMessage","Desc  should be atleast 10 chars");
			return "todo";
		}
		
		service.addTodo(getLoggedInUserName(model), todo.getDesc(), todo.getTargetDate(), false);
		return "redirect:/list-todos";
	}
}
