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
	
	@SuppressWarnings("resource")
	public static void createItem(TodoList list) {
		
		String title, desc,cate,due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Create item Section ==========\n");
		
		System.out.println("enter the category :");
		cate = sc.nextLine();
		System.out.println("enter the title : ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		sc.nextLine();
		System.out.println("enter the description :");
		desc = sc.nextLine();
		
		System.out.println("enter the due date :");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, cate, due_date);
		if(list.addItem(t)>0)
			System.out.println("========== Item Added! ==========");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Delete Item Section ==========\n"
				+ "enter the number of item to remove\n"
				+ "\n");
		int no = sc.nextInt();
		if(l.deleteItem(no)>0)
			System.out.println("========== Item Deleted! ==========");
	}


	@SuppressWarnings("resource")
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Edit Item Section ==========\n"
				+ "enter the number of the item you want to update\n"
				+ "\n");
		int no = sc.nextInt();
		System.out.println("enter the new title of the item");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}

		sc.nextLine();
		System.out.println("enter the new description : ");
		String new_description = sc.nextLine().trim();
		System.out.println("enter the new category :");
		String new_cate = sc.nextLine();
		System.out.println("enter the new due date :");
		String new_due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(new_title,new_description,new_cate,new_due_date);
		t.setId(no);
		if(l.editItem(t)>0)
			System.out.println("수정되었습니다.");
	}

	public static void listAll(TodoList l,String orderby, int ordering) {
		System.out.println("========== Item List ==========");
		System.out.println("Item total: " + l.getCount() + " items!");
		for(TodoItem item : l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
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
	
	public static void findByWord(TodoList l, String name) {
		int count=0;
		for (TodoItem item : l.getList(name)) {
			System.out.println(item);
			count++;
		}
		System.out.println("Found a total of " + count + " items!");
	}
	public static void findCate(TodoList l, String cate) {
		int no=0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item);
			no++;
		}
		System.out.println("Found a total of " + no + " items!");
	}
	
	@SuppressWarnings("unused")
	public static void lsCate(TodoList l) {
		int items=0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			items++;
		}
		System.out.println("\nFound a total of " + items + " items!");
	}
	public static void completeItem(TodoList l, int no) {
		for(TodoItem item : l.getList()) {
			if(item.getId()==no)
				item.setIs_completed(1);
		}
		if(l.completeItem(no)>0)
			System.out.println("========== Item Completed! ==========");
	}
	public static void listAll(TodoList l, int no) {
		int items=0;
		for(TodoItem item : l.getList(no)) {
			System.out.println(item);
			items++;
		}
		System.out.println("\nFound a total of " + items + " items!");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
