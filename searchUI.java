package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchUI extends JFrame implements ActionListener {
    JPanel jp = new JPanel();
    JButton index = new JButton("新建索引库");
    JTextField field = new JTextField("请先输入你要查询的文件夹并创建索引",40);
    JButton sb = new JButton("查找内容");
    Container c=getContentPane();
    public searchUI(String stitle){
        super(stitle);

        setLayout(null);
        centerWindow();
        setSize(450, 250);

        field.setSize(300,30);
        c.add(field);

        addbtn();

    }



    private void addbtn(){
        index.setBounds(250, 100, 100, 30);
        c.add(index);
        index.addActionListener(this);
        sb.setBounds(80, 100, 100, 30);
        sb.addActionListener(this);
        c.add(sb);

    }
    public void actionPerformed(ActionEvent e){

        String ItemName = e.getActionCommand();
        if (e.getSource()==index){//点击了新建索引按钮
            try{ws.WriteDirectlib.deleteAllIndex(field.getText());
            ws.WriteDirectlib.writedirec(field.getText());
                field.setText("创建成功，输入内容搜索！");
            }
            catch (Exception a){
                field.setText("WARNING:创建索引库失败！");
                a.printStackTrace();
            }
        }
        if (e.getSource()==sb){//点击了查找按钮
           String s=field.getText();
            try{selectfil sf=new selectfil(ws.Search.search(s));
                sf.list.setVisible(true);
            }
            catch(Exception a){
                a.printStackTrace();
            }

        }

    }

    public void centerWindow(){
//获得显示屏桌面窗口的大小
        Toolkit tk=getToolkit();
        Dimension dm=tk.getScreenSize();
//让窗口居中显示
        setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
    }
}
