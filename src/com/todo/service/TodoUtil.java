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
		list.addItem(t);
		System.out.println("========== Item Added! ==========");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Delete Item Section ==========\n"
				+ "enter the number of item to remove\n"
				+ "\n");
		
//		String title = sc.next();
		int no = sc.nextInt();
		int iterator=0;
		for (TodoItem item : l.getList()) {
			iterator++;
			if (no == iterator) {
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
				+ "enter the number of the item you want to update\n"
				+ "\n");
		int no = sc.nextInt();
//		String title = sc.next().trim();
//		if (!l.isDuplicate(title)) {
//			System.out.println("title doesn't exist");
//			return;
//		}
		
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
		int iterator=0;
		for (TodoItem item : l.getList()) {
			iterator++;
			if (no == iterator) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description,new_cate,new_due_date);
				l.addItem(t);
				System.out.println("========== Item updated! ==========");
			}
		}
	}

	public static void listAll(TodoList l) {
		int iterator=0;
		System.out.println("========== Item List ==========");
		for (TodoItem item : l.getList()) {
			System.out.println(iterator+1 + ".{" + item.getCategory() + "} Title: " + 
							"[" + 
							item.getTitle() + 
							"]" + 
							"  Item Description:  " +  
							"[" + item.getDesc() + 
							"] - " + 
							item.getDue_date() + 
							" - " +
							item.getString_date());
			iterator++;
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
		int no=0;
		for(TodoItem item : l.getList()) {
			if(item.getTitle().contains(name)) {
				System.out.println(no+1 + ".{" + item.getCategory() + "} Title: " + 
						"[" + 
						item.getTitle() + 
						"]" + 
						"  Item Description:  " +  
						"[" + item.getDesc() + 
						"] - " + 
						item.getDue_date() + 
						" - " +
						item.getString_date());
				no++;
			}
			else if(item.getCategory().contains(name)) {
				System.out.println(no+1 + ".{" + item.getCategory() + "} Title: " + 
						"[" + 
						item.getTitle() + 
						"]" + 
						"  Item Description:  " +  
						"[" + item.getDesc() + 
						"] - " + 
						item.getDue_date() + 
						" - " +
						item.getString_date());
				no++;
			}
			else if(item.getDesc().contains(name)) {
				System.out.println(no+1 + ".{" + item.getCategory() + "} Title: " + 
						"[" + 
						item.getTitle() + 
						"]" + 
						"  Item Description:  " +  
						"[" + item.getDesc() + 
						"] - " + 
						item.getDue_date() + 
						" - " +
						item.getString_date());
				no++;
			}
		}
		System.out.println("Found a total of " + no + " items!");
	}
	public static void findCate(TodoList l, String cate) {
		int no=0;
		for(TodoItem item : l.getList()) {
			if(item.getCategory().equals(cate)) {
				System.out.println(no+1 + ".{" + item.getCategory() + "} Title: " + 
						"[" + 
						item.getTitle() + 
						"]" + 
						"  Item Description:  " +  
						"[" + item.getDesc() + 
						"] - " + 
						item.getDue_date() + 
						" - " +
						item.getString_date());
				no++;
			}
		}
		System.out.println("Found a total of " + no + " items!");
	}
	
	public static void lsCate(TodoList l) {
		int no=0;
		for(TodoItem item : l.getList()) {
			no++;
		}
		int items=0;
		String[] temp= new String[no];
		int i=0;
		for(TodoItem item : l.getList()) {
			temp[i] = item.getCategory();
			i++;
		}
		for(i=0;i<no;i++) {
			if(temp[i]==null) continue;
			for(int j=i+1;j<no;j++) {
				if(temp[i].equals(temp[j])) temp[j]=null;
			}

			System.out.print("[" + temp[i] + "] ");
			items++;
		}
		System.out.println("\nFound a total of " + items + " items!");

		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
