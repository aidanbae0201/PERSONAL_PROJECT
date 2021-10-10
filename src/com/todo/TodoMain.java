package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.connectSQL();
		l.importData("todolist.txt");
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
				TodoUtil.listAll(l,"id",1);
				break;

			case "ls_name":
				System.out.println("List is now sorted by title.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("List is now sorted by title in descending order.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("List is now sorted by due_date.");
				TodoUtil.listAll(l, "due_date", 1);
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
				TodoUtil.findByWord(l,sc.nextLine().trim());
				break;
			case "ls_date_desc":
				System.out.println("List is now sorted by due_date in descending order.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
			case "find_cate":
				TodoUtil.findCate(l,sc.nextLine().trim());
				break;
			case "ls_cate":
				TodoUtil.lsCate(l);
				break;
			case "comp":
				TodoUtil.completeItem(l,sc.nextInt());
				// add method
				break;
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				// add method
				break;
			default:
				System.out.println("Enter valid command (type \"help\" for list of commands");
				break;
			}
			
		} while (!quit);
	}
}
