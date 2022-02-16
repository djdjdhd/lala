package ui;

import ui.bag.TestJFrameExtends;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class selectfil extends JFrame implements ActionListener {
     JFrame list=new JFrame("符合条件的文件");
    Container c=getContentPane();
    JButton []btn={
            new JButton("无"),new JButton("无"),new JButton("无"),new JButton("无")
    };
    ArrayList<String> DD=new ArrayList<>();
    public selectfil(ArrayList<String> D){

        DD=D;
        list.setLayout(null);
        list.setSize(300, 300);
        centerWindow();
        for(int i=0;i<D.size()/2;i++){
            File f=new File(D.get(i*2));
            btn[i].setText(f.getName());
            System.out.println(f.getName());
            btn[i].setBounds(50, 100+30*i, 200, 30);
            btn[i].addActionListener(this);
            list.add(btn[i]);
        }
        if(D.size()==0){
            btn[3].setText("WARNING:没有找到文件");
            btn[3].setBounds(50, 100, 200, 30);
            btn[3].addActionListener(this);
            list.add(btn[3]);
        }





    }
    public void actionPerformed(ActionEvent e){



        if (e.getSource()==btn[0]){
            try{

                TestJFrameExtends.ta.setEditorKit(new HTMLEditorKit());

                TestJFrameExtends.ta.setText(DD.get(1));
            }
            catch (Exception a){
                a.printStackTrace();
            }
        }
        if (e.getSource()==btn[1]){
            try{
                //TestJFrameExtends.ta.setContentType("text/html");//DD.get(1)
                TestJFrameExtends.ta.setEditorKit(new HTMLEditorKit());
                TestJFrameExtends.ta.setText(DD.get(3));
            }
            catch (Exception a){
                a.printStackTrace();
            }
        }
        if (e.getSource()==btn[2]){
            try{
                TestJFrameExtends.ta.setEditorKit(new HTMLEditorKit());
                TestJFrameExtends.ta.setText(DD.get(5));

            }
            catch (Exception a){
                a.printStackTrace();
            }
        }
        if (e.getSource()==btn[3]){
            try{
                list.setVisible(false);

            }
            catch (Exception a){
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
