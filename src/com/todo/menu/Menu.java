package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
    	System.out.println();
    	System.out.println("<ToDoList ���� ��ɾ� ����>");
        System.out.println("add - �׸� �߰�");
        System.out.println("del - �׸� ����");
        System.out.println("edit - �׸� ����");
        System.out.println("ls - ��ü ���");
        System.out.println("ls_name_asc - ����� ����");
        System.out.println("ls_name_desc - ���񿪼� ����");
        System.out.println("ls_date - ��¥�� ����");
        System.out.println("ls_date_desc - ��¥���� ����");
        System.out.println("find - ���� �� ���� �˻�");
        System.out.println("find_cate - ī�װ� �˻�");
        System.out.println("ls_cate - ī�װ� ���");
        System.out.println("comp num - �׸� �Ϸ�");
        System.out.println("ls_comp - �Ϸ�� �׸� ���");
        System.out.println("mustDo num - �� �ؾ��ϴ� �׸� üũ");
        System.out.println("ls_mustDo - �� �ؾ��ϴ� �׸� ���");
        System.out.println("save - Json ����");
        System.out.println("load - Json �ε�");
        System.out.println("exit - ����");
    }
    public static void prompt(){ 
    System.out.println("\nCommand > ");
}
}