package com.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;


import com.todo.service.DbConnect;

public class TodoList {
	private List<TodoItem> list;
	Connection conn;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
		this.conn = DbConnect.getConnection();
	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, category, reason, time, current_date, due_date, is_completed, mustDo)" + " values (?,?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getReason());
			pstmt.setString(5, t.getTime());
			pstmt.setString(6, t.getCurrent_date());
			pstmt.setString(7, t.getDue_date());
			pstmt.setInt(8, t.get_is_completed());
			pstmt.setInt(9, t.get_mustDo());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int num) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, reason=?, time=?, current_date=?, due_date=?, is_completed=?, mustDo=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getReason());
			pstmt.setString(5, t.getTime());
			pstmt.setString(6, t.getCurrent_date());
			pstmt.setString(7, t.getDue_date());
			pstmt.setInt(8, t.get_is_completed());
			pstmt.setInt(9, t.get_mustDo());
			pstmt.setInt(10, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String reason = rs.getString("reason");
				String time = rs.getString("time");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				int mustDo = rs.getInt("mustDo");
				
				TodoItem t = new TodoItem(title, description, category, reason, time, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_mustDo(mustDo);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%" + keyword + "%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String reason = rs.getString("reason");
				String time = rs.getString("time");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				int mustDo = rs.getInt("mustDo");
				
				TodoItem t = new TodoItem(title, description, category, reason, time, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_mustDo(mustDo);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList(int num){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE is_completed = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String reason = rs.getString("reason");
				String time = rs.getString("time");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				int mustDo = rs.getInt("mustDo");
				TodoItem t = new TodoItem(title, description, category,reason,time, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_mustDo(mustDo);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String category = rs.getString("category");
				
				list.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String cate){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cate);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String reason = rs.getString("reason");
				String time = rs.getString("time");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				int mustDo = rs.getInt("mustDo");
				TodoItem t = new TodoItem(title, description, category,reason,time, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_mustDo(mustDo);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY " + orderby;
			if(ordering == 0)
				sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String reason = rs.getString("reason");
				String time = rs.getString("time");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				int mustDo = rs.getInt("mustDo");
				TodoItem t = new TodoItem(title, description, category, reason, time, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_mustDo(mustDo);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getMustDoList(int num){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE mustDo = ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String reason = rs.getString("reason");
				String time = rs.getString("time");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				int mustDo = rs.getInt("mustDo");
				
				TodoItem t = new TodoItem(title, description, category, reason, time, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.set_is_completed(is_completed);
				t.set_mustDo(mustDo);
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int completeItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, reason=?, time=?, current_date=?, due_date=?, is_completed=?, is_mustDo=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getReason());
			pstmt.setString(5, t.getTime());
			pstmt.setString(6, t.getCurrent_date());
			pstmt.setString(7, t.getDue_date());
			pstmt.setInt(8, t.get_is_completed());
			pstmt.setInt(9, t.get_mustDo());
			pstmt.setInt(10, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int mustDo(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, reason=?, time=?, current_date=?, due_date=?, is_completed=?, mustDo=?" + " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getReason());
			pstmt.setString(5, t.getTime());
			pstmt.setString(6, t.getCurrent_date());
			pstmt.setString(7, t.getDue_date());
			pstmt.setInt(8, t.get_is_completed());
			pstmt.setInt(9, t.get_mustDo());
			pstmt.setInt(10, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : getList()) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	
	public int getSize() {
		return list.size();
	}
	
	/*public void importData(String filename) throws IOException, SQLException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title, memo, category, reason, time, current_date, due_date)" + " values (?,?,?,?,?,?,?)";
			
			int records = 0;
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String description = st.nextToken();
				String reason = st.nextToken();
				String time = st.netToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,title);
				pstmt.setString(2,description);
				pstmt.setString(3,category);
				pstmt.setString(4,reason);
				pstmt.setString(5,time);
				pstmt.setString(6,current_date);
				pstmt.setString(7,due_date);
				int count = pstmt.executeUpdate();
				if(count>0) records++;
				pstmt.close();
			}
			System.out.println(records + " records read success!!");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}*/
}