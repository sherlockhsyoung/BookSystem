package com.HuangSiYang.BMS.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
 
class MainViewFrame extends JFrame{
	/**操作主界面
	 * 
	 */
	private static final long serialVersionUID = 2;
	JButton search,delete,add,modify,borrow,giveBack,show,bookBorrowed,studentBorrowed;
	JLabel title;
	JPanel up = new JPanel();
	JPanel center = new JPanel();
	JPanel down = new JPanel();
	JPanel east = new JPanel();
	JPanel west = new JPanel();
    MainViewFrame(){
		
		setTitle("欢迎进入图书管理系统");
		setSize(900, 600);
		setLocationRelativeTo(null);//基本设置
		setLayout(new BorderLayout());
		Container Content = this.getContentPane();
		center.setLayout(new GridLayout(3,3,140,60));
		east.setPreferredSize(new Dimension(120,500));
		west.setPreferredSize(new Dimension(120,500));//设置panel
		down.setPreferredSize(new Dimension(700,100));
		up.setPreferredSize(new Dimension(700,140));
		title  = new JLabel("请选择以下操作");			//设置标题
		title.setFont(new Font("微软雅黑",Font.BOLD, 25));
		title.setPreferredSize(new Dimension(200,100));
		add = new JButton("录入书籍");					//录入按钮
		add.addActionListener(new AddActionListener());
		delete = new JButton("下架书籍");				//下架按钮
		delete.addActionListener(new DeleteActionListener());
		modify = new JButton("修改书籍");				//修改按钮
		modify.addActionListener(new ModifyActionListener());
		search = new JButton("查找书籍");				//查找按钮
		search.addActionListener(new SearchActionListener());
		borrow = new JButton("借阅书籍");				//借阅按钮
		borrow.addActionListener(new BorrowActionListener());
		giveBack = new JButton("归还书籍");			//归还按钮
		giveBack.addActionListener(new GiveBackActionListener());
	    show = new JButton("书籍列表");				//展示书籍按钮
	    show.addActionListener(new ShowActionListener());
		bookBorrowed = new JButton("书籍借阅情况");		//查看书籍借阅情况按钮
	    bookBorrowed.addActionListener(new BookBorrowedActionListener());
	    studentBorrowed = new JButton("学生借阅情况");	//查看学生借阅情况按钮
	    studentBorrowed.addActionListener(new StudentBorrowedActionListener());
	    //添加各个组件
		up.add(title);
		center.add(add);
		center.add(delete);
		center.add(modify);
		center.add(search);			
		center.add(borrow);
		center.add(giveBack);						
		center.add(show);
		center.add(bookBorrowed);
		center.add(studentBorrowed);
		Content.add(up, BorderLayout.NORTH);
		Content.add(center,BorderLayout.CENTER);
		Content.add(down, BorderLayout.SOUTH);
		Content.add(east, BorderLayout.EAST);
		Content.add(west, BorderLayout.WEST);
		setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗口 
	
	}
	class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new  AddJFrame();
		}
	}
	class DeleteActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new DeleteFrame();
		}
	}
	class ModifyActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new ModifyJFrame();
		}
	}
	class SearchActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new SearchFrame();
		}
	}
	class BorrowActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new BorrowFrame();
		}
	}
	class GiveBackActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new ReturnFrame();
		}
	}
	class ShowActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				new ShowAllFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class BookBorrowedActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				new BookBorrowedFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class StudentBorrowedActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				new StudentBorrowedFrame();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}







