package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String string_date;
    private Date current_date;
    private String category;
    private String due_date;
    private int id;
    private int is_completed;

    @Override
	public String toString() {
    	if(this.is_completed==1)
    		return (id + ",{" + this.getCategory() + "} Title: " +
    				"[" + this.getTitle() + "] " +
    				"[V] " + 
    				"Item Description: " + "[" + this.getDesc() + "] - " +
    				this.getDue_date() + " - " +
    				this.getString_date());
    	else {
    		return (id + ",{" + this.getCategory() + "} Title: " +
    				"[" + this.getTitle() + "] " +
    				"Item Description: " + "[" + this.getDesc() + "] - " +
    				this.getDue_date() + " - " +
    				this.getString_date());
    	}
	}
    	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        this.current_date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.string_date=format.format(current_date);
    }


	public TodoItem(String title, String desc, String category, String due_date, int is_completed) {
		super();
		this.title = title;
		this.desc = desc;
		this.category = category;
		this.due_date = due_date;
		this.is_completed = is_completed;
	}

	public TodoItem(String title, String desc, String category, String due_date) {
		super();
		this.title = title;
		this.desc = desc;
		this.category = category;
		this.due_date = due_date;
		this.current_date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.string_date = format.format(current_date);
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCurrent_date() {
        return current_date;
    }
    
    public String getString_date() {
    	return string_date;
    }

    public void setCurrent_date(Date current_date) {
        this.current_date = current_date;
    }
    public void setString_date(String date) {
    	this.string_date = date;
    }
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + string_date + "\n";
    }

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
}
