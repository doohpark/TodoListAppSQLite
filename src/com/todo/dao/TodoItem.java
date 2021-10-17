 package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
	private int id;
	private int is_completed;
	private int mustDo;
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private String reason;
    private String time;

    public TodoItem(String title, String desc, String category, String reason, String time, String due_date){
        this.title=title;
        this.desc=desc;
        this.category=category;
        this.time = time;
        this.reason=reason;
        this.due_date=due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
        this.is_completed = 0;
        this.mustDo = 0;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }
    
    public void set_is_completed(int num) {
    	this.is_completed = num;
    }
    
    public int get_is_completed() {
    	return is_completed;
    }
    public void set_mustDo(int num) {
    	this.mustDo=num;
    }
    public int get_mustDo() {
    	return mustDo;
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
    
    public String getReason() {
    	return reason;
    }
    public void setReason(String reason) {
    	this.reason=reason;
    }
    
    public String getTime() {
    	return time;
    }
    public void setTime(String time) {
    	this.time=time;
    }
    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
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
    
    @Override
    public String toString() {
    	if(this.is_completed == 0) {
    		if(this.mustDo==0) {
    			return id + " [" + category + "] " + title + " - " + desc + " - "+ reason + " - " + time + " - " + due_date + " - " + current_date;
    		}
    		else {
    			return id +" [***] " +" [" + category + "] " + title + " - " + desc + " - "+ reason + " - " + time + " - " + due_date + " - " + current_date;
    		}
    	}
    	else {
    		if(this.mustDo==0) {
    			return id + " [" + category + "] " + title + " [V] " + " - " + desc + " - "+ reason + " - " + time + " - " + due_date + " - " + current_date;
    	}
    		else {
    			return id  +" [***] " + " [" + category + "] " + title + " [V] " + " - " + desc + " - "+ reason + " - " + time + " - " + due_date + " - " + current_date;
    		}
    }
}
}