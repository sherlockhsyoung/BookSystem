package com.HuangSiYang.BMS.view;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.HuangSiYang.BMS.service.BookService;
import com.HuangSiYang.BMS.service.ShowService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

class AddJFrame extends JFrame {
	/**录入书籍界面
	 * 
	 */
	private static final long serialVersionUID = 5;
	JLabel id,name,author,press,stockAll,stockNow;
	JTextField idField,nameField,authorField,pressField,stockAllField,stockNowField;
	JButton add;
	AddJFrame(){
		setTitle("录入书籍");
		setSize(800, 600);//基本设置
		setLocationRelativeTo(null);
		setLayout(null);
		JLabel title;//标题
		title = new JLabel("请输入要录入的书籍的信息:");
		title.setFont(new Font("微软雅黑",Font.BOLD, 20));
		title.setBounds(100,20,280,60);
		//编号
		id = new JLabel("书籍编号：");
		id.setFont(new Font("微软雅黑",Font.BOLD, 16));
		id.setBounds(120,100,100,50);
		idField = new JTextField (10);
		idField.setBounds(220,100,280,40);
		//书名
		name = new JLabel("书名：");
		name.setFont(new Font("微软雅黑",Font.BOLD, 16));
		name.setBounds(150,150,100,50);
		nameField = new JTextField (20);
		nameField.setBounds(220,150,280,40);
		//作者
		author = new JLabel("作者：");
		author.setFont(new Font("微软雅黑",Font.BOLD, 16));
		author.setBounds(150,200,100,50);
		authorField = new JTextField (20);
		authorField.setBounds(220,200,280,40);
		//出版社
		press = new JLabel("出版社：");
		press.setFont(new Font("微软雅黑",Font.BOLD, 16));
		press.setBounds(135,250,100,50);
		pressField = new JTextField (20);
		pressField.setBounds(220,250,280,40);
		//总库存
		stockAll = new JLabel("总库存：");
		stockAll.setFont(new Font("微软雅黑",Font.BOLD, 16));
		stockAll.setBounds(135,300,100,50);
		stockAllField = new JTextField (20);
		stockAllField.setBounds(220,300,280,40);
		//现库存
		stockNow = new JLabel("现库存：");
		stockNow.setFont(new Font("微软雅黑",Font.BOLD, 16));
		stockNow.setBounds(135,350,100,50);
		stockNowField = new JTextField (20);
		stockNowField.setBounds(220,350,280,40);
		//录入按钮
		add = new JButton("录入");
		add.setBounds(300,430, 70, 50);
		add.addActionListener(new AddBookActionListener());
		//添加组件
		add(title);
		add(id);
		add(idField);
		add(name);
		add(nameField);
		add(author);
		add(authorField);
		add(press);
		add(pressField);
		add(stockAll);
		add(stockAllField);
		add(stockNow);
		add(stockNowField);
		add(add);
		setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	}
	class AddBookActionListener implements ActionListener{
		private void cleanInput() {
			idField.setText(null);
			nameField.setText(null);
			authorField.setText(null);
			pressField.setText(null);
			stockAllField.setText(null);
			stockNowField.setText(null);
		}//清空输入
		public void actionPerformed(ActionEvent e) {
				
			String id = idField.getText() ;
			String name = nameField.getText();
			String author = authorField.getText();		//拿到输入的信息
			String press = pressField.getText();
			String stockAll = stockAllField.getText();
			String stockNow = stockNowField.getText();
			try {
				BookService bd = new BookService();
				int result = bd.addBookService(id, name, author, press, stockAll, stockNow);
				if(result == 0) {
					new AddFailureFrame();
					cleanInput();
				}//添加失败
				else {
					new AddSuccessFrame();
					cleanInput();
				}//添加成功
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class AddSuccessFrame extends JFrame{
		/**录入书籍成功提示界面
		 * 
		 */
		private static final long serialVersionUID = 6;
		AddSuccessFrame(){
			//基本设置
			setTitle("提示");
			setSize(350, 350);
			setLocationRelativeTo(null);
			setLayout(null);
			JLabel title=new JLabel("录入成功！");
			title.setBounds(120, 40, 200, 100);
			title.setFont(new Font("微软雅黑",Font.BOLD, 25));
			add(title);
			setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口
		}
	}
	class AddFailureFrame extends JFrame{
		/**录入失败提示界面
		 * 
		 */
		private static final long serialVersionUID = 7;
		AddFailureFrame(){
			//基本设置
			setTitle("提示");
			setSize(350, 350);
			setLocationRelativeTo(null);
			setLayout(null);
			JLabel title1=new JLabel("录入失败！");
			title1.setBounds(120, 30, 250, 80);
			title1.setFont(new Font("微软雅黑",Font.BOLD, 22));
			JLabel title2=new JLabel("请重新录入");
			title2.setBounds(110, 70, 200, 100);
			title2.setFont(new Font("微软雅黑",Font.BOLD, 22));
			add(title1);
			add(title2);
			setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口
		}
	}
}
class DeleteFrame extends JFrame{
	/**下架书籍界面
	 * 
	 */
	private static final long serialVersionUID = -1341786058617727703L;
	JTextField idField;
	JButton delete;
	DeleteFrame(){
		//基本设置
		setTitle("下架书籍");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setLayout(null);
		//标题
		JLabel title = new JLabel("请输入要下架的书籍的编号:");
		title.setFont(new Font("微软雅黑",Font.BOLD, 25));
		title.setBounds(210,120,350,60);
		//输入框
		idField = new JTextField(10);
		idField.setBounds(220,250,280,40);
		//删除按钮
		delete = new JButton("下架");
		delete.setBounds(320,430, 70, 50);
	    delete.addActionListener(new DeleteBookActionListener());
		add(title);
		add(idField);
		add(delete);
		setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	}
	class DeleteBookActionListener implements ActionListener{
		private void cleanInput() {
			idField.setText(null);
		}//清空输入
		public void actionPerformed(ActionEvent e){
			String id = idField.getText();
			try {
				BookService bd = new BookService();
				int result = bd.deleBookService(id);
				if (result == 1) {
					new DeleteSuccessFrame();
					cleanInput();
				}//删除成功
				else {
					new DeleteFailureFrame();
					cleanInput();
				}//删除失败
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class DeleteSuccessFrame extends JFrame{
		/**下架成功提示界面
		 * 
		 */
		private static final long serialVersionUID = 9;
		DeleteSuccessFrame(){
			
			setTitle("提示");
			setSize(350, 350);
			setLocationRelativeTo(null);
			setLayout(null);
			JLabel title=new JLabel("下架成功！");
			title.setBounds(120, 40, 200, 100);
			title.setFont(new Font("微软雅黑",Font.BOLD, 25));
			add(title);
			setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗口
		}
	}
	class DeleteFailureFrame extends JFrame{
		/**下架失败提示界面
		 * 
		 */
		private static final long serialVersionUID = 10;
		DeleteFailureFrame(){
			//基本设置
			setTitle("提示");
			setSize(350, 350);
			setLocationRelativeTo(null);
			setLayout(null);
			JLabel title1=new JLabel("下架失败！");
			title1.setBounds(120, 30, 250, 80);
			title1.setFont(new Font("微软雅黑",Font.BOLD, 23));
			JLabel title2=new JLabel("请输入正确的编号");
			title2.setBounds(85, 70, 200, 100);
			title2.setFont(new Font("微软雅黑",Font.BOLD, 22));
			add(title1);
			add(title2);
			setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口
		}
	}
}
class ModifyJFrame extends JFrame {
	/**更新图书的操作界面
	 * 
	 */
	private static final long serialVersionUID = 11;
	JLabel id,name,author,press,stockAll,stockNow;
	JTextField idField,nameField,authorField,pressField,stockAllField,stockNowField;
	JButton add;
	ModifyJFrame(){
		//基本设置
		setTitle("修改书籍");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setLayout(null);
		//标题
		JLabel title;
		title = new JLabel("请输入要修改的书籍的信息:");
		title.setFont(new Font("微软雅黑",Font.BOLD, 20));
		title.setBounds(100,20,280,60);
		//编号
		id = new JLabel("书籍编号：");
		id.setFont(new Font("微软雅黑",Font.BOLD, 16));
		id.setBounds(120,100,100,50);
		idField = new JTextField (10);
		idField.setBounds(220,100,280,40);
		//书名
		name = new JLabel("书名：");
		name.setFont(new Font("微软雅黑",Font.BOLD, 16));
		name.setBounds(150,150,100,50);
		nameField = new JTextField (20);
		nameField.setBounds(220,150,280,40);
		//作者
		author = new JLabel("作者：");
		author.setFont(new Font("微软雅黑",Font.BOLD, 16));
		author.setBounds(150,200,100,50);
		authorField = new JTextField (20);
		authorField.setBounds(220,200,280,40);
		//出版社
		press = new JLabel("出版社：");
		press.setFont(new Font("微软雅黑",Font.BOLD, 16));
		press.setBounds(135,250,100,50);
		pressField = new JTextField (20);
		pressField.setBounds(220,250,280,40);
		//总库存
		stockAll = new JLabel("总库存：");
		stockAll.setFont(new Font("微软雅黑",Font.BOLD, 16));
		stockAll.setBounds(135,300,100,50);
		stockAllField = new JTextField (20);
		stockAllField.setBounds(220,300,280,40);
		//现库存
		stockNow = new JLabel("现库存：");
		stockNow.setFont(new Font("微软雅黑",Font.BOLD, 16));
		stockNow.setBounds(135,350,100,50);
		stockNowField = new JTextField (20);
		stockNowField.setBounds(220,350,280,40);
		//修改按钮
		add = new JButton("修改");
		add.setBounds(300,430, 70, 50);
		add.addActionListener(new ModifyBookActionListener());
		//添加组件
		add(title);
		add(id);
		add(idField);
		add(name);
		add(nameField);
		add(author);
		add(authorField);
		add(press);
		add(pressField);
		add(stockAll);
		add(stockAllField);
		add(stockNow);
		add(stockNowField);
		add(add);
		setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	}
	class ModifyBookActionListener implements ActionListener{
		
		private void cleanInput() {
			idField.setText(null);
			nameField.setText(null);
			authorField.setText(null);
			pressField.setText(null);
			stockAllField.setText(null);
			stockNowField.setText(null);
		}//清空输入
		public void actionPerformed(ActionEvent e) {
			
			String id = idField.getText() ;
			String name = nameField.getText();
			String author = authorField.getText();
			String press = pressField.getText();			//拿到输入的信息
			String stockAll = stockAllField.getText();
			String stockNow = stockNowField.getText();
			try {
				BookService bd = new BookService();
				int result =bd.modifyBookService(id, name, author, press, stockAll, stockNow);
				if(result == 1) {
					new ModifySuccessFrame();
					cleanInput();
				}//修改成功
				else {
					new ModifyFailureFrame();
					cleanInput();
				}//修改失败
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class ModifySuccessFrame extends JFrame{
		/**更新成功提示界面
		 * 
		 */
		private static final long serialVersionUID = 12;
		ModifySuccessFrame(){
			//基本设置
			setTitle("提示");
			setSize(350, 350);
			setLocationRelativeTo(null);
			setLayout(null);
			JLabel title=new JLabel("修改成功！");
			title.setBounds(120, 40, 200, 100);
			title.setFont(new Font("微软雅黑",Font.BOLD, 25));
			add(title);
			setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口
		}
	}
	class ModifyFailureFrame extends JFrame{
		/**更新失败提示界面
		 * 
		 */
		private static final long serialVersionUID = 13;
		ModifyFailureFrame(){
			//基本设置
			setTitle("提示");
			setSize(350, 350);
			setLocationRelativeTo(null);
			setLayout(null);
			JLabel title1=new JLabel("修改失败！");
			title1.setBounds(120, 30, 250, 80);
			title1.setFont(new Font("微软雅黑",Font.BOLD, 22));
			JLabel title2=new JLabel("请重新修改");
			title2.setBounds(110, 70, 200, 100);
			title2.setFont(new Font("微软雅黑",Font.BOLD, 22));
			add(title1);add(title2);
			setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口
		}
	}
}
class SearchFrame extends JFrame{
	/**查找图书界面
	 * 
	 */
	private static final long serialVersionUID = 5198448975165917003L;
	JTable table;
	JTextField bookname;
	JButton search,clean;
	JScrollPane jsp = null;//可以实现滚动的
	final Vector<Object> data = new Vector<Object>();//动态数组
	final Vector<String> columnNames = new Vector<String>();//不可更改其中的数据
	JPanel center = new JPanel();//三个面板
	JPanel up = new JPanel();
	JPanel down = new JPanel();
	SearchFrame(){
		//基本设置
		setLayout(new BorderLayout());
		setSize(900,600);
		setTitle("查找书籍");
		setLocationRelativeTo(null);
		//添加容器
		Container Content = this.getContentPane();
		center.setLayout(new BorderLayout());
		//标题
		JLabel lable = new JLabel("请输入要查找的书籍名：");
		bookname = new JTextField();
		bookname.setPreferredSize(new Dimension(300,40));//设置输入框大小
		//查找按钮
		search = new JButton("查找");  
		search.addActionListener(new SearchActionListener());
		//清空按钮
		clean = new JButton("清空");
		clean.addActionListener(new CleanActionListener());
		//默认显示书籍信息栏
		columnNames.add("编号");
		columnNames.add("书名");
		columnNames.add("作者");
		columnNames.add("出版社");
		columnNames.add("总库存");
		columnNames.add("现库存");
		//设置表
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(850, 500));
		table.setRowHeight (25);//设置每行的高度为20
		table.setSelectionForeground (Color.BLUE);//设置所选择行的前景色
		//添加组件
		jsp=new JScrollPane(table);
		up.add(lable);
		up.add(bookname);
		up.add(search);
		center.add(jsp);
		down.add(clean);
		Content.add(center, BorderLayout.CENTER);
		Content.add(up, BorderLayout.NORTH);
		Content.add(down, BorderLayout.SOUTH);
		setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	}
	class SearchActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String bookName = bookname.getText();
			try {
				BookService bd = new BookService();
				boolean judge = bd.judge(bookName);
				if(judge == true) {
				DefaultTableModel model = new DefaultTableModel();
				 model = bd.seachBookService(bookName);
				 table.setModel(model);//刷新面版
				 }else 
				 	{	new SearchFailureFrame();
				 }
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class CleanActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			bookname.setText(null);
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);
		}
	}//清空输入
	class SearchFailureFrame extends JFrame{
		/**查找失败提示界面
		 * 
		 */
		private static final long serialVersionUID = 15;
		SearchFailureFrame(){
			//基本设置
			setTitle("提示");
			setSize(350, 300);
			setLocationRelativeTo(null);
			setLayout(null);
			JLabel title1=new JLabel("抱歉！");
			title1.setBounds(120, 30, 250, 80);
			title1.setFont(new Font("微软雅黑",Font.BOLD, 23));
			JLabel title2=new JLabel("找不到该书籍");
			title2.setBounds(85, 70, 200, 100);
			title2.setFont(new Font("微软雅黑",Font.BOLD, 22));
			add(title1);
			add(title2);
			setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口
		}
	}
}
class BorrowFrame extends JFrame{
	/**
	 * 借阅界面
	 */
	private static final long serialVersionUID = -6546720985109041685L;
	JTextField bookId;
	JButton borrow;
	JPanel up = new JPanel();
	JPanel center = new JPanel();
	JPanel down = new JPanel();
	BookService bd = new BookService();
	BorrowFrame(){
		
		setLayout(new BorderLayout());
		setSize(700,500);			//基本设置
		setTitle("借阅书籍");
		setLocationRelativeTo(null);
		Container Content = this.getContentPane();
	    JLabel lable = new JLabel("请输入要借阅的书籍编号：");
	    lable.setFont(new Font("微软雅黑",Font.BOLD, 25));
	    bookId = new JTextField();
	    bookId.setPreferredSize(new Dimension(300,40));//设置输入框大小
	    borrow = new JButton("借阅");
	    borrow.setPreferredSize(new Dimension(80,60));
	    borrow.addActionListener(new BorrowActionListener());
	    center.setPreferredSize(new Dimension(300,200));
	    center.add(lable);
	    center.add(bookId);
	    down.setPreferredSize(new Dimension(500,200));
	    down.add(borrow);
	    Content.add(up, BorderLayout.NORTH);
	    Content.add(center, BorderLayout.CENTER);
		Content.add(down, BorderLayout.SOUTH);
		pack();
		setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	} 
	class BorrowActionListener implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			String book_id = bookId.getText();
			try {
				int result = bd.borrowBookService(book_id);
				if(result == 1) {
					//借阅成功
					new SuccessFrame();
				}else {
					//借阅失败
					new FailureFrame();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	class SuccessFrame extends JFrame{
		/**
		 * 借阅成功提示界面
		 */
		private static final long serialVersionUID = -9005815353027348940L;
		SuccessFrame(){
			setSize(300,250);
			setTitle("提示");
			setLocationRelativeTo(null);
			JLabel lable = new JLabel("借阅成功！");
		    lable.setFont(new Font("微软雅黑",Font.BOLD, 30));
		    add(lable);
		    setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
		    
		}
	}
	class FailureFrame extends JFrame{
		/**
		 * 借阅失败提示界面
		 */
		private static final long serialVersionUID = 1L;

		FailureFrame(){
			setSize(350,280);
			setTitle("提示");
			setLocationRelativeTo(null);
			JLabel lable2 = new JLabel("输入的编号不存在");
		    lable2.setFont(new Font("微软雅黑",Font.BOLD, 32));
		    add(lable2);
		    setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
		}
	}
}
class ReturnFrame extends JFrame{
	/**归还界面
	 * 
	 */
	private static final long serialVersionUID = -3198037363190094839L;
	JTextField bookId;
	JButton giveBack;
	JPanel up = new JPanel();
	JPanel center = new JPanel();
	JPanel down = new JPanel();
	BookService bd = new BookService();
	ReturnFrame(){
		//基本设置
		setLayout(new BorderLayout());
		setSize(700,500);
		setTitle("归还书籍");
		setLocationRelativeTo(null);
		setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	    Container Content = this.getContentPane();
	    JLabel lable = new JLabel("请输入要归还的书籍编号：");
	    lable.setFont(new Font("微软雅黑",Font.BOLD, 25));
	    bookId = new JTextField();
	    bookId.setPreferredSize(new Dimension(300,40));//设置输入框大小
	    giveBack = new JButton("归还");
	    giveBack.setPreferredSize(new Dimension(80,60));
	    giveBack.addActionListener(new GiveBackActionListener());
	    center.setPreferredSize(new Dimension(300,200));
	    center.add(lable);
	    center.add(bookId);
	    down.setPreferredSize(new Dimension(500,200));
	    down.add(giveBack);
	    Content.add(up, BorderLayout.NORTH);
	    Content.add(center, BorderLayout.CENTER);
		Content.add(down, BorderLayout.SOUTH);
		pack();
		
	}
	class GiveBackActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String book_id = bookId.getText();
			try {
				int result = bd.returnBookService(book_id);
				if(result == 1) {
					//归还成功
					new SuccessFrame();
				}else {
					//归还失败
					new FailureFrame();
				}
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	class SuccessFrame extends JFrame{
		/**
		 * 归还成功提示界面
		 */
		private static final long serialVersionUID = -4554263180652419024L;
		SuccessFrame(){
			setSize(300,250);
			setTitle("提示");
			setLocationRelativeTo(null);
			JLabel lable = new JLabel("归还成功！");
		    lable.setFont(new Font("微软雅黑",Font.BOLD, 30));
		    add(lable);
		    setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
		 }
	}
	class FailureFrame extends JFrame{
		/**
		 * 归还失败提示界面
		 */
		private static final long serialVersionUID = -3323894779127022662L;
		FailureFrame(){
			setSize(350,280);
			setTitle("提示");
			setLocationRelativeTo(null);
			JLabel lable2 = new JLabel("借阅超时 请到前台归还");
		    lable2.setFont(new Font("微软雅黑",Font.BOLD, 28));
		    add(lable2);
		    setVisible(true);//显示窗口
		    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
		}
	}
}
class ShowAllFrame extends JFrame{
	/**展示所有书籍界面
	 * 
	 */
	private static final long serialVersionUID = 4854451232867480840L;
	JTable table;
	JPanel center = new JPanel();
	JScrollPane jsp = null;//可以实现滚动的
	ShowAllFrame() throws Exception{
		//基本设置
		setLayout(new BorderLayout());
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("展示书籍");
		setLocationRelativeTo(null);
		Container Content = this.getContentPane();
	  	center.setLayout(new BorderLayout());
	    //设置表
	  	table = new JTable();
	  	ShowService ss = new ShowService();//调用service层
	  	DefaultTableModel model = ss.showAllBook();
	  	table.setModel(model);
	  	table.setPreferredScrollableViewportSize(getMaximumSize());
	  	table.setRowHeight (30);//设置每行的高度为20
	  	table.setSelectionForeground (Color.BLUE);//设置所选择行的前景色
	  	jsp=new JScrollPane(table);
	  	center.add(jsp);
	  	Content.add(center, BorderLayout.CENTER);
	  	setVisible(true);//显示窗口
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	}
}
class BookBorrowedFrame extends JFrame{
	/**展示某本书籍被借阅的情况界面
	 * 
	 */
	private static final long serialVersionUID = -4210592001558185459L;

	JTable table;
	JTextField bookId;
	JButton see,clean;
	JScrollPane jsp = null;//可以实现滚动的
	JPanel center = new JPanel();//三个面板
	JPanel up = new JPanel();
	JPanel down = new JPanel();
	BookBorrowedFrame(){
		
		setLayout(new BorderLayout());
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("查看书籍借阅情况");
		setLocationRelativeTo(null);
		Container Content = this.getContentPane();
		center.setLayout(new BorderLayout());
		JLabel lable = new JLabel("请输入要查看的书籍编号：");//标题
		bookId = new JTextField();
		bookId.setPreferredSize(new Dimension(300,40));//设置输入框大小
		see = new JButton("查看");  //查看按钮
		see.addActionListener(new SeeActionListener());
		clean = new JButton("清空");//清空按钮
		clean.addActionListener(new CleanActionListener());
		//设置表
		table = new JTable();
		table.setPreferredScrollableViewportSize(getMaximumSize());
		table.setRowHeight (30);//设置每行的高度为20
		table.setSelectionForeground (Color.BLUE);//设置所选择行的前景色
		jsp=new JScrollPane(table);
		up.add(lable);
		up.add(bookId);
		up.add(see);		
		center.add(jsp);		//添加组件
		down.add(clean);
		Content.add(center, BorderLayout.CENTER);
		Content.add(up, BorderLayout.NORTH);
		Content.add(down, BorderLayout.SOUTH);
		setVisible(true);//显示窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
				
	}
	class SeeActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String Id = bookId.getText();
			ShowService ss = new ShowService();//调用service层
			DefaultTableModel model;
			try {
				model = ss.showBookBorrowed(Id);
				table.setModel(model);//刷新面版
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class CleanActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			bookId.setText(null);
			table.setModel(new DefaultTableModel());
		}
	}//清空输入
}
class StudentBorrowedFrame extends JFrame{
	/**展示某位学生借阅书籍情况的界面
	 * 
	 */
	private static final long serialVersionUID = -5274947335580860841L;
	JTable table;
	JTextField studentName;
	JButton see,clean;
	JScrollPane jsp = null;//可以实现滚动的
	JPanel center = new JPanel();//三个面板
	JPanel up = new JPanel();
	JPanel down = new JPanel();
	StudentBorrowedFrame(){
		setLayout(new BorderLayout());
		setExtendedState(MAXIMIZED_BOTH);//基本设置
		setTitle("查看学生借阅情况");
		setLocationRelativeTo(null);
		Container Content = this.getContentPane();//设置容器
		center.setLayout(new BorderLayout());
		JLabel lable = new JLabel("请输入要查看的学生姓名：");//设置标题
		studentName = new JTextField();
		studentName.setPreferredSize(new Dimension(300,40));//设置输入框大小
		see = new JButton("查看");  //查看按钮
		see.addActionListener(new SeeActionListener());
		clean = new JButton("清空");	//清空按钮
		clean.addActionListener(new CleanActionListener());
		table = new JTable();//设置表
		table.setPreferredScrollableViewportSize(getMaximumSize());
		table.setRowHeight (30);//设置每行的高度为20
		table.setSelectionForeground (Color.BLUE);//设置所选择行的前景色
		jsp=new JScrollPane(table);
		up.add(lable);
		up.add(studentName);
		up.add(see);			//添加组件
		center.add(jsp);
		down.add(clean);
		Content.add(center, BorderLayout.CENTER);
		Content.add(up, BorderLayout.NORTH);	//添加面板
		Content.add(down, BorderLayout.SOUTH);
		setVisible(true);//显示窗口
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  //关闭窗口 
	}
class SeeActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String name = studentName.getText();
			ShowService ss = new ShowService();//调用service层
			DefaultTableModel model;//初始model
			try {
				model = ss.showStudentBorrowed(name);
				table.setModel(model);//刷新面版
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class CleanActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			studentName.setText(null);
			table.setModel(new DefaultTableModel());
		}
	}//清空输入
}
