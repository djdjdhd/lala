package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchUI extends JFrame implements ActionListener {
    JPanel jp = new JPanel();
    JButton index = new JButton("�½�������");
    JTextField field = new JTextField("����������Ҫ��ѯ���ļ��в���������",40);
    JButton sb = new JButton("��������");
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
        if (e.getSource()==index){//������½�������ť
            try{ws.WriteDirectlib.deleteAllIndex(field.getText());
            ws.WriteDirectlib.writedirec(field.getText());
                field.setText("�����ɹ�����������������");
            }
            catch (Exception a){
                field.setText("WARNING:����������ʧ�ܣ�");
                a.printStackTrace();
            }
        }
        if (e.getSource()==sb){//����˲��Ұ�ť
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
//�����ʾ�����洰�ڵĴ�С
        Toolkit tk=getToolkit();
        Dimension dm=tk.getScreenSize();
//�ô��ھ�����ʾ
        setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
    }
}
