package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l,"todolist.txt");
		Menu.displaymenu();
		do {
			isList = false;
			Menu.prompt();
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("List is now sorted in ascending order.");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				System.out.println("List is now sorted in descending order.");
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("List is now sorted by date.");
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				TodoUtil.saveList(l,"todolist.txt");
				System.out.println("All data have been saved!");
				quit = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break; 
			case "find":
				TodoUtil.findByWord(l,sc.next());
				break;
			case "ls_date_desc":
				System.out.println("List is now sorted by date in descending order.");
				l.sortByDate();
				l.reverseList();
				isList=true;
				break;
			case "find_cate":
				TodoUtil.findCate(l,sc.next());
				break;
			case "ls_cate":
				TodoUtil.lsCate(l);
				break;
			default:
				System.out.println("Enter valid command (type \"help\" for list of commands");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
