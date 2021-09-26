package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;
import com.todo.service.TodoSortByDate_reverse;


public class TodoList {
	private List<TodoItem> list;
	private HashSet<String> ls_cate = new HashSet<String>();
	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(int num) {
		list.remove(list.get(num));
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n"
				+ "inside list_All method\n");
		for (TodoItem myitem : list) {
			System.out.println(list.indexOf(myitem)+1 +  ". " + "[" + myitem.getCategory() + "] " + myitem.getTitle() +  " - " + myitem.getDesc() + " - " + myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
		}
	}
	public void listAll_title_desc(String key) {
		int count_key=0;
		for(TodoItem myitem : list) {
			if(myitem.getTitle().contains(key)) {
				System.out.println(list.indexOf(myitem)+1+". "+ "[" + myitem.getCategory() + "] " + myitem.getTitle() +  " - " + myitem.getDesc() + " - " +myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
				count_key ++;
			}
			if(myitem.getDesc().contains(key)) {
				System.out.println(list.indexOf(myitem)+1 +  ". " + "[" + myitem.getCategory() + "] " + myitem.getTitle() +  " - " + myitem.getDesc() + " - " + myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
				count_key++;
			}
		}
		System.out.println("ÃÑ " + count_key + "°³ Ã£À½");
	}
	
	public void listAll_category(String key) {
		int count_key=0;
		for(TodoItem myitem:list) {
			if(myitem.getCategory().contains(key)) {
				System.out.println(list.indexOf(myitem)+1 +  ". " + "[" + myitem.getCategory() +"] " + myitem.getTitle() +  " - " + myitem.getDesc() + " - " +myitem.getDue_date() + " <" + myitem.getCurrent_date() + "> ");
				count_key ++;
			}
		}
		System.out.println("ÃÑ "+count_key + "°³ Ã£À½");
	}
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}
	public void sortByDate_reverse() {
		Collections.sort(list, new TodoSortByDate_reverse());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	public int getSize() {
		return list.size();
	}
	public void search_cate() {
		for(TodoItem item : list) {
			ls_cate.add(item.getCategory());
		}
	}
	public void show_ls_cate() {
		search_cate();
		Iterator<String> iterset = ls_cate.iterator();
		while(iterset.hasNext()) {
			System.out.print("<"+iterset.next()+"> ");
		}
		System.out.println("\nÃÑ "+ls_cate.size()+ "°³ÀÇ Ä«Å×°í¸®");
	}
}
