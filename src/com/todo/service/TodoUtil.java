package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		
		System.out.println("Enter priority level 1~5 (1 is most important, 5 is least important) : ");
		int priority = sc.nextInt();
		
		TodoItem t = new TodoItem(title, desc, cate, due_date,priority);
		if(list.addItem(t)>0)
			System.out.println("========== Item Added! ==========");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Delete Item Section ==========\n"
				+ "enter the number of item(s) to remove");
		String line = sc.nextLine();
		String[] no = line.split(" ");
		int count=0;
		for(String num:no) {
			int number = Integer.parseInt(num);
			if(l.deleteItem(number)>0)
				count++;
		}
		if(count == no.length)
			System.out.println("========== Item Deleted! ==========");
		else
			System.out.println("========== Deletion Failed! ==========");
	}


	@SuppressWarnings("resource")
	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "========== Edit Item Section ==========\n"
				+ "enter the number of the item you want to update");
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
		System.out.println("enter the new category : ");
		String new_cate = sc.nextLine();
		System.out.println("enter the new due date : ");
		String new_due_date = sc.nextLine();
		System.out.println("enter the new priority level : ");
		int priority = sc.nextInt();
		TodoItem t = new TodoItem(new_title,new_description,new_cate,new_due_date,priority);
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
	public static void completeItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of item(s) to complete");
		String line = sc.nextLine();
		String[] no = line.split(" ");
		int count=0;
		for(String num:no) {
			int number = Integer.parseInt(num);
			for(TodoItem item: l.getList())
				if(item.getId()==number)
					item.setIs_completed(1);
			if(l.completeItem(number)>0)
				count++;
		}
		if(count == no.length)
			System.out.println("========== Item Completed! ==========");
		else
			System.out.println("========== Completion Failed! ==========");
	}

	public static void listAll(TodoList l, int no) {
		int items=0;
		for(TodoItem item : l.getList(no)) {
			System.out.println(item);
			items++;
		}
		System.out.println("\nFound a total of " + items + " items!");
	}
	public static void toJson(TodoList l) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		List<TodoItem> list = new ArrayList<TodoItem>();
		for(TodoItem item: l.getList()) {
			list.add(item);
		}
		String jsonstr = gson.toJson(list);
		try {
			FileWriter writer = new FileWriter("data2.txt");
			writer.write(jsonstr);
			writer.close();
			System.out.println("Saved in file!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void fromJson(TodoList l) {
		Gson gson = new Gson();
		String jsonstr=null;
		try {
			BufferedReader br = new BufferedReader(new FileReader("data2.txt"));
			jsonstr = br.readLine();
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Data read succesfully from file!");
		TodoItem[] array = gson.fromJson(jsonstr,TodoItem[].class);
		List<TodoItem> list = Arrays.asList(array);
		
	}
	
	public static void progressItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of item(s) to mark as in progress");
		String line = sc.nextLine();
		String[] no = line.split(" ");
		int count=0;
		for(String num: no) {
			int number = Integer.parseInt(num);
			for(TodoItem item: l.getList())
				if(item.getId() == number)
					item.setIn_progress(1);
			if(l.progressItem(number)>0)
				count++;
		}
		if(count == no.length)
			System.out.println("========== In progress set! ==========");
		else
			System.out.println("========== In progress failed! ==========");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
