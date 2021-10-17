package com.todo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
	private String category;
    private String title;
    private String desc;
    private String due_date;
    private String string_date;
    private int is_completed;
    private Date current_date;
    private int priority;
    private int days_left;
    private int in_progress;
    

    @Override
	public String toString() {
    	if(this.is_completed==1)
    		return (id + ".[" + this.getCategory() + "]  " +
    				"[V] [" + 
    				this.priority + "] - " +
    				this.getTitle() + ": " +
    				this.getDesc() + " - " +
    				this.getDue_date() + " - " +
    				this.getString_date());
    	else if(this.in_progress==1)
    		return (id + ".[" + this.getCategory() + "]  " +
    				"[ ] [" + 
    				this.priority + "] - " +
    				this.getTitle() + ": " +
    				this.getDesc() + " - " +
    				this.getDays_left() + " day(s) left! - " +
    				this.getDue_date() + " - " +
    				this.getString_date() + " - IN PROGRESS!");
    	else {
    		return (id + ".[" + this.getCategory() + "]  " +
    				"[ ] [" + 
    				this.priority + "] - " +
    				this.getTitle() + ": " +
    				this.getDesc() + " - " +
    				this.getDays_left() + " day(s) left! - " +
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

	public TodoItem(String title, String desc, String category, String due_date,int priority) {
		super();
		this.title = title;
		this.desc = desc;
		this.category = category;
		this.due_date = due_date;
		this.priority = priority;
		this.current_date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.string_date = format.format(current_date);
	}
	
	public TodoItem(String title, String desc, String category, String due_date,int is_completed,int priority,int in_progress) {
		super();
		this.title = title;
		this.desc = desc;
		this.category = category;
		this.due_date = due_date;
		this.is_completed = is_completed;
		this.priority = priority;
		this.in_progress = in_progress;
		this.current_date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.string_date = format.format(current_date);
	}
	
	public TodoItem(String title, String desc, String category, String due_date,int is_completed,int priority) {
		super();
		this.title = title;
		this.desc = desc;
		this.category = category;
		this.due_date = due_date;
		this.is_completed = is_completed;
		this.priority = priority;
		this.current_date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.string_date = format.format(current_date);
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
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + string_date + "##" + priority + "##" + is_completed + "##" + in_progress + "\n";
    }

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public long getDays_left() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date d2 = sdf.parse(this.due_date);
			long dif = d2.getTime() - this.getCurrent_date().getTime();
			long days_left = (dif/ (1000 * 60 * 60 * 24))% 365;
			return days_left+1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days_left;
		
	}

	public void setDays_left(int days_left) {
		this.days_left = days_left;
	}

	public int getIn_progress() {
		return in_progress;
	}

	public void setIn_progress(int in_progress) {
		this.in_progress = in_progress;
	}
}
