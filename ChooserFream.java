package changeExeToExcel;

import java.io.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.*;
public class ChooserFream implements ActionListener{
    JFrame frame=new JFrame("�ļ�ѡ����");
    JTabbedPane tabPane=new JTabbedPane();//ѡ�����
    Container con=new Container();//����1
    JLabel label1=new JLabel("ѡ��Ŀ¼���ļ�");
    JLabel labelSuccess=new JLabel("");
    JTextField text1=new JTextField();
    JButton button1=new JButton("ѡ��");
    JButton button2=new JButton("ת����ԭ�ļ���");
    JButton button3=new JButton("ת��������");
    JFileChooser jfc=new JFileChooser();//�ļ�ѡ����
    File file=null;
    String newPath=null;
    FileSystemView fsv = FileSystemView.getFileSystemView();
	File com=fsv.getHomeDirectory();   //����Ƕ�ȡ����·���ķ�����
    public ChooserFream(){
        jfc.setCurrentDirectory(com);//�ļ�ѡ�����ĳ�ʼĿ¼��Ϊ����
        //����������ȡ����Ļ�ĸ߶ȺͿ��
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//�趨���ڳ���λ��
        frame.setSize(400,200);//�趨���ڴ�С
        frame.setContentPane(tabPane);//���ò���
       //�����趨��ǩ�ȵĳ���λ�ú͸߿�
        label1.setBounds(10,10,100,20);
        text1.setBounds(120,10,160,20);
        button1.setBounds(300,10,60,20);
        button2.setBounds(10,40,140,20);
        button3.setBounds(170,40,140,20);
        labelSuccess.setBounds(20,70,100,20);
        button1.addActionListener(this);//����¼�����
        button2.addActionListener(this);
        button3.addActionListener(this);
        con.add(label1);
        con.add(text1);
        con.add(button1);
        con.add(button2);
        con.add(button3);
        con.add(labelSuccess);
        con.add(jfc);
        tabPane.add("Ŀ¼/�ļ�ѡ��",con);//��Ӳ���1
        frame.setVisible(true);//���ڿɼ�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ʹ�ܹرմ��ڣ���������
    }
    public void actionPerformed(ActionEvent e){//�¼�����
        if(e.getSource().equals(button1)){//�жϴ��������İ�ť���ĸ�
            //jfc.setFileSelectionMode(1);//�趨ֻ��ѡ���ļ���
           // jfc.setFileSelectionMode(0);�趨ֻ��ѡ���ļ�
            int state=jfc.showOpenDialog(null);//�˾��Ǵ��ļ�ѡ��������Ĵ������
            if(state==1){
                return;//�����򷵻�
            }
            else{
                file=jfc.getSelectedFile();//fΪѡ�񵽵�Ŀ¼
                text1.setText(file.getAbsolutePath());
            }
        }
        if(e.getSource().equals(button2)){//ת����ԭ�ļ���
        	try {
        		newPath=file.getAbsolutePath();
        		newPath=newPath.replace(".txt", ".xls");
				new Test1().change(file,newPath);
				labelSuccess.setText("�޸ĳɹ�");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        if(e.getSource().equals(button3)){//ת��������
        	try {System.out.println("asfadadas");
        		String newName=file.getName().replace(".txt", ".xls");
        		newPath=com.getAbsolutePath()+"\\"+newName;
        		System.out.println(newPath);
				new Test1().change(file,newPath);
				labelSuccess.setText("�޸ĳɹ�");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        
        }
    }
    public static void main(String[] args) {
        new ChooserFream();
    }
}