package changeExeToExcel;

import java.io.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.*;
public class ChooserFream implements ActionListener{
    JFrame frame=new JFrame("文件选择器");
    JTabbedPane tabPane=new JTabbedPane();//选项卡布局
    Container con=new Container();//布局1
    JLabel label1=new JLabel("选择目录或文件");
    JLabel labelSuccess=new JLabel("");
    JTextField text1=new JTextField();
    JButton button1=new JButton("选择");
    JButton button2=new JButton("转换到原文件夹");
    JButton button3=new JButton("转换到桌面");
    JFileChooser jfc=new JFileChooser();//文件选择器
    File file=null;
    String newPath=null;
    FileSystemView fsv = FileSystemView.getFileSystemView();
	File com=fsv.getHomeDirectory();   //这便是读取桌面路径的方法了
    public ChooserFream(){
        jfc.setCurrentDirectory(com);//文件选择器的初始目录定为桌面
        //下面两行是取得屏幕的高度和宽度
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//设定窗口出现位置
        frame.setSize(400,200);//设定窗口大小
        frame.setContentPane(tabPane);//设置布局
       //下面设定标签等的出现位置和高宽
        label1.setBounds(10,10,100,20);
        text1.setBounds(120,10,160,20);
        button1.setBounds(300,10,60,20);
        button2.setBounds(10,40,140,20);
        button3.setBounds(170,40,140,20);
        labelSuccess.setBounds(20,70,100,20);
        button1.addActionListener(this);//添加事件处理
        button2.addActionListener(this);
        button3.addActionListener(this);
        con.add(label1);
        con.add(text1);
        con.add(button1);
        con.add(button2);
        con.add(button3);
        con.add(labelSuccess);
        con.add(jfc);
        tabPane.add("目录/文件选择",con);//添加布局1
        frame.setVisible(true);//窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使能关闭窗口，结束程序
    }
    public void actionPerformed(ActionEvent e){//事件处理
        if(e.getSource().equals(button1)){//判断触发方法的按钮是哪个
            //jfc.setFileSelectionMode(1);//设定只能选择到文件夹
           // jfc.setFileSelectionMode(0);设定只能选择到文件
            int state=jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                file=jfc.getSelectedFile();//f为选择到的目录
                text1.setText(file.getAbsolutePath());
            }
        }
        if(e.getSource().equals(button2)){//转换到原文件夹
        	try {
        		newPath=file.getAbsolutePath();
        		newPath=newPath.replace(".txt", ".xls");
				new Test1().change(file,newPath);
				labelSuccess.setText("修改成功");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        if(e.getSource().equals(button3)){//转换到桌面
        	try {System.out.println("asfadadas");
        		String newName=file.getName().replace(".txt", ".xls");
        		newPath=com.getAbsolutePath()+"\\"+newName;
        		System.out.println(newPath);
				new Test1().change(file,newPath);
				labelSuccess.setText("修改成功");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        
        }
    }
    public static void main(String[] args) {
        new ChooserFream();
    }
}