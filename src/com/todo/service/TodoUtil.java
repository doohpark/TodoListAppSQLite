package com.todo.service;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, reason, time, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�߰�]");
		System.out.println("ī�װ� > ");
		category = sc.next();
		System.out.println("���� > ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.printf("���� �ߺ� �Ұ�!");
			return;
		}
		
		sc.nextLine();
		System.out.println("���� > ");
		desc = sc.nextLine().trim();
		System.out.println("���� > ");
		reason = sc.nextLine().trim();
		System.out.println("�� �� �ϴµ� �ɸ��� �ð� > ");
		time = sc.next();
		sc.nextLine();
		System.out.print("���� > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, reason, time, due_date);
		if(list.addItem(t)>0)
			System.out.println("�߰� �Ϸ�!");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[����]\n" + "��ȣ > ");
		
		int count =0;
		String multiInput = sc.nextLine();
		String[] num = multiInput.split(",");
		
		/*if (num<=0 || num > l.getSize()) {
			System.out.printf("���� �ʰ�!");
			return;
		}*/

		listAll(l);
		
		System.out.println("���� �ұ��? (y/n)");
		String yn = sc.next();
		
		if("y".equalsIgnoreCase(yn)) {
			for(String index : num) {
				if(l.deleteItem(Integer.parseInt(index))>0)
					count++;
			}
			System.out.println(count + " �� ���� �Ϸ�!");
		}
		else {
			System.out.println("���� ���!");
			return;
		}
	}


	public static void updateItem(TodoList l) {	
		String new_title, new_desc, new_category, new_reason, new_time, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[����]\n" + "��ȣ > ");
		int num = sc.nextInt();
		
		/*if (num<=0 || num > l.getSize()) {
			System.out.printf("���� �ʰ�!");
			return;
		}*/
		
		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("���� �ߺ� �Ұ�!");
			return;
		}
		
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		
		System.out.print("�� ���� > ");
		new_desc = sc.nextLine().trim();
		
		System.out.print("�� ���� > ");
		new_reason = sc.nextLine().trim();
		
		System.out.print("�� �ѹ� �ϴµ� �ɸ��� �ð� > ");
		new_time = sc.next();
		sc.nextLine();
		
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_reason, new_time, new_due_date);
		t.setId(num);
		
		if(l.updateItem(t)>0) {
			System.out.println("���� �Ϸ�!.");
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("�� %d���� �׸��� ã��.\n",count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("\n�� %d���� �׸��� ã��.\n",count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵ�.\n", count);
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int num) {
		int count = 0;
		for(TodoItem item : l.getList(num)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("�� %d���� �׸��� ã��.\n",count);
	}
	
	public static void listAllMustDo(TodoList l, int num) {
		int count = 0;
		for(TodoItem item : l.getMustDoList(num)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.println("�� " + count + "���� �׸��� ã��.");
	}
	public static void completeItem(TodoList l, int number) {
		for(TodoItem item : l.getList()) {
			if(item.getId() == number) {
				String new_title = item.getTitle();
				String new_desc = item.getDesc();
				String new_category = item.getCategory();
				String new_reason = item.getReason();
				String new_time = item.getTime();
				String new_due_date = item.getDue_date();
				
				TodoItem t = new TodoItem(new_title, new_desc, new_category, new_reason, new_time, new_due_date);
				t.set_is_completed(1);
				t.setId(number);
				System.out.println(t.toString());
				if(l.completeItem(t)>0) {
					System.out.println("�ش� �׸��� �Ϸ� ó����.\n");
				}
				
			}
		}
	}
	
	public static void mustDo(TodoList l, int num) {
		for(TodoItem item : l.getList()) {
			if(item.getId() == num) {
				String new_title = item.getTitle();
				String new_desc = item.getDesc();
				String new_category = item.getCategory();
				String new_reason = item.getReason();
				String new_time = item.getTime();
				String new_due_date = item.getDue_date();
				
				TodoItem t = new TodoItem(new_title, new_desc, new_category, new_reason, new_time, new_due_date);
				t.set_mustDo(1);
				t.setId(num);
				System.out.println(t.toString());
				if(l.mustDo(t)>0) {
					System.out.println("�� �ؾ��ϴ� �׸����� ó����.\n");
				}
				
			}
		}
	}
	
	public static void saveJson(TodoList l) {
		Scanner s = new Scanner(System.in);
		Gson gson = new Gson();
		
		System.out.println("Json �������� ����");
		System.out.print("���� �̸�: ");
		
		String filename = s.nextLine();
		String jsonstr = gson.toJson(l.getList());
		
		try {
			FileWriter writer = new FileWriter(filename);
			writer.write(jsonstr);
			writer.close();
			System.out.println(filename + " ���� �Ϸ�!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadJson(TodoList l) {
		Scanner s = new Scanner(System.in);
		Gson gson = new Gson();
		
		System.out.println("Json ���� ���� �ε�");
		System.out.print("���� �̸�: ");
		
		String filename = s.nextLine();
		String jsonstr=null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			jsonstr = br.readLine();
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println(filename + " ���� ����!");
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<TodoItem> list = gson.fromJson(jsonstr,  new TypeToken<List<TodoItem>>() {}.getType());
		
		int count =0;
		for(TodoItem item : list) {
			if(l.addItem(item)>0)
				count++;
		}
		if(count == list.size()) {
			System.out.println(count +" records from " + filename + " �ε� �Ϸ�!");
		}
	}
}
