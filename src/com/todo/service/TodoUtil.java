package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[추가]");
		System.out.println("카테고리 > ");
		category = sc.next();
		System.out.println("제목 > ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.printf("제목 중복 불가!");
			return;
		}
		
		sc.nextLine();
		System.out.println("설명 > ");
		desc = sc.nextLine().trim();
		System.out.print("마감 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t)>0)
			System.out.println("추가 완료!");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("[삭제]\n" + "번호 > ");
		
		int num = sc.nextInt();
		
		/*if (num<=0 || num > l.getSize()) {
			System.out.printf("범위 초과!");
			return;
		}*/

		listAll(l);
		
		System.out.println("삭제 할까요? (y/n)");
		String yn = sc.next();
		
		if("y".equalsIgnoreCase(yn)) {
			l.deleteItem(num);
			System.out.println("삭제 완료!");
		}
		else {
			System.out.println("삭제 취소!");
			return;
		}
	}


	public static void updateItem(TodoList l) {	
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[변경]\n" + "번호 > ");
		int num = sc.nextInt();
		
		/*if (num<=0 || num > l.getSize()) {
			System.out.printf("범위 초과!");
			return;
		}*/
		
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("제목 중복 불가!");
			return;
		}
		
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(num);

		if(l.updateItem(t)>0) {
			System.out.println("변경 완료!.");
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("총 %d개의 항목을 찾음.\n",count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("\n총 %d개의 항목을 찾음.\n",count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count ++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록됨.\n", count);
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for(TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
		
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
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
		System.out.printf("총 %d개의 항목을 찾음.\n",count);
	}
	
	public static void completeItem(TodoList l, int number) {
		for(TodoItem item : l.getList()) {
			if(item.getId() == number) {
				String new_title = item.getTitle();
				String new_desc = item.getDesc();
				String new_category = item.getCategory();
				String new_due_date = item.getDue_date();
				
				TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
				t.set_is_completed(1);
				t.setId(number);
				System.out.println(t.toString());
				if(l.completeItem(t)>0) {
					System.out.println("해당 항목이 완료 처리됨.\n");
				}
				
			}
		}
	}
}
