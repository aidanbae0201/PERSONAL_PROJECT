package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("Command List: ");
        System.out.println("add - Add a new item");
        System.out.println("comp - complete an item");
        System.out.println("del - Delete an existing item");
        System.out.println("edit - Update an item");
        System.out.println("find <KEYWORD> - find an item containing KEYWORD");
        System.out.println("find_cate <KEYWORD> - find a category containing KEYWORD");
        System.out.println("ls - List all items");
        System.out.println("ls_comp - List all items completed");
        System.out.println("ls_name - sort the list by name");
        System.out.println("ls_name_desc - sort the list by name");
        System.out.println("ls_date - sort the list by date");
        System.out.println("ls_date_desc - sort the list by date in descending order");
        System.out.println("ls_prior - sort the list by priority");
        System.out.println("progress - Mark an item as in progress");
        System.out.println("exit - or press ESC to exit");
        System.out.println();
    }
    
    public static void prompt()
    {
    	System.out.print("Enter your command > ");
    }
}
