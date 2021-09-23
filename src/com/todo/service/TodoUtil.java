package com.todo.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Scanner;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Create item Section ==========\n"
				+ "enter the title : \n");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.println("enter the description");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("========== Item Added! ==========");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("\n"
				+ "========== Delete Item Section ==========\n"
				+ "enter the title of item to remove\n"
				+ "\n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		System.out.println("========== Item Deleted! ==========");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Edit Item Section ==========\n"
				+ "enter the title of the item you want to update\n"
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("title doesn't exist");
			return;
		}
		
		System.out.println("enter the new title of the item");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}

		sc.nextLine();
		System.out.println("enter the new description ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("========== Item updated! ==========");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("========== Item List ==========");
		for (TodoItem item : l.getList()) {
			System.out.println("Item Title: " + "[" + item.getTitle() + "]" + "  Item Description:  " +  "[" + item.getDesc() + "] - " + item.getString_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		Writer w;
		try {
			w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		Reader r;
		try {
			r = new FileReader(filename);
			int data;
			while((data = r.read()) != -1) {
				System.out.print((char)data);
			}
			r.close();
		} catch (FileNotFoundException e) {
			System.out.println("todolist.txt file not found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
