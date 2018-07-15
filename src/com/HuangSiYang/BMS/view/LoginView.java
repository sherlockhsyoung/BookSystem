package com.HuangSiYang.BMS.view;
import com.HuangSiYang.BMS.service.BookService;
import com.HuangSiYang.BMS.service.LoginService;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginView {
	public static void main(String[] args) throws Exception{
		LoginView lv = new LoginView();
		lv.new LoginJFrame();
	
	 }
	class LoginJFrame extends JFrame {
		/**学生登录的界面
		 * 
		 */
		private static final long serialVersionUID = 1;
			JTextField idField,nameField;//定义全局变量
			JLabel title,idLabel,nameLabel;
			JButton login,cancel;
			LoginJFrame(){
				setTitle("图书管理系统");
				setSize(650, 450);
				setLocationRelativeTo(null);//基本设置
				setLayout(null);
				//标题
				title= new JLabel("欢迎进入本图书管理系统");
				title.setFont(new Font("微软雅黑",Font.BOLD, 30));
				title.setBounds(150, 40, 340, 100);
				//学号
		        idLabel  = new JLabel("学号：");
		        idField = new JTextField (10);
		        idLabel.setBounds(180, 180, 50, 30);
		        idField.setBounds(220, 180, 220, 30);
		        //姓名
		        nameLabel = new JLabel("姓名:");
		        nameField = new JTextField(10);
		        nameLabel.setBounds(180, 220, 50, 30);
		        nameField.setBounds(220, 220, 220, 30);
		        //登录
		        login = new JButton("登录");
		        login.setBounds(240, 280, 60, 40);
		        login.addActionListener(new loginListener());
				//取消
		        cancel = new JButton("取消");
		        cancel.setBounds(340, 280, 60, 40);
		        cancel.addActionListener(new cancelListenner());
		        //添加组件
		        add(title);
		        add(idLabel);
		        add(idField);
		        add(nameLabel);
		        add(nameField);
		        add(login);
		        add(cancel);
		        setVisible(true);//显示窗口
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗口 
		       
		}
			class loginListener implements ActionListener{
				
				private void cleanInput() {
					idField.setText(null);
					nameField.setText(null);
				}//清空输入的信息
				
				public void actionPerformed(ActionEvent e) {
					
					String idGetted = idField.getText();//获取输入的学号
					String nameGetted = nameField.getText();//获取输入的姓名
					BookService bd = new BookService();
					try {
						bd.loginInfoService(idGetted, nameGetted);//将登录信息存入loginInfoService中
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					try {
						LoginService ls = new LoginService();
						int result = ls.loginService(idGetted,nameGetted);//将登录信息传入loginService
						if(result == 0) {
							new LoginFaiureFrame();
							cleanInput();
						}//输入为空或学号输入不合法
						if(result == 1) {
							new AutoRegisterSuccessFrame();
						}//自动注册成功
						if(result == 2) {
							new MainViewFrame();
							setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭登录窗口
							dispose();
						}//登录成功，跳至主界面
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			class cancelListenner implements ActionListener{
				
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			}
			class AutoRegisterSuccessFrame extends JFrame{
				/**自动登录成功 的提示界面
				 * 
				 */
				private static final long serialVersionUID = 4;
				JButton sure;
				AutoRegisterSuccessFrame(){
					
					setTitle("提示");
					setSize(350, 350);
					setLocationRelativeTo(null);
					setLayout(null);
					JLabel title=new JLabel("已自动注册成功！");
					title.setBounds(80, 40, 200, 100);
					title.setFont(new Font("微软雅黑",Font.BOLD, 25));
					sure = new JButton("确定");
					sure.setBounds(140, 180, 60, 40);
					sure.addActionListener(new SureListener());
					add(sure);//添加组件
					add(title);
					setVisible(true);//显示窗口
				    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗口
				    
				}
				class SureListener implements ActionListener{
					
					public void actionPerformed(ActionEvent e) {
						dispose();//关闭本窗口
						new MainViewFrame();//转到主界面
					}
				}
			}
			class LoginFaiureFrame extends JFrame{
					/**登录失败的界面
					 * 
					 */
					private static final long serialVersionUID = 3;
					LoginFaiureFrame(){
						
						setTitle("提示");
						setSize(350, 350);
						setLocationRelativeTo(null);
						setLayout(null);
						JLabel title1=new JLabel("输入为空或学号不合法");
						title1.setBounds(60, 30, 250, 80);
						title1.setFont(new Font("微软雅黑",Font.BOLD, 22));
						JLabel title2=new JLabel("请重新输入");
						title2.setBounds(110, 70, 200, 100);
						title2.setFont(new Font("微软雅黑",Font.BOLD, 22));
						add(title1);
						add(title2);
						setVisible(true);//显示窗口
					    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭窗口
						
					}
			}
	}
}




