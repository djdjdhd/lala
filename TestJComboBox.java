package ui.bag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestJComboBox extends JFrame implements ItemListener {
    //�������С������
    JComboBox cbxFont=new JComboBox();
    JComboBox cbxFontSize=new JComboBox();//�����С

     public void itemStateChanged(ItemEvent e){
        GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[]  fontList=ge.getAvailableFontFamilyNames();
        Object obj=cbxFontSize.getSelectedItem();
        int a=Integer.parseInt(obj.toString());
        TestJFrameExtends.ta.setFont(new Font(fontList[cbxFont.getSelectedIndex()], NORMAL,a));
    }
    TestJComboBox(String sTitle){
        super(sTitle);

        Container c = getContentPane( );
        c.setLayout(new FlowLayout(FlowLayout.LEFT));

        c.add(new JLabel("�������ƣ�"));
        c.add(cbxFont);
        c.add(new JLabel("�����С��"));
        c.add(cbxFontSize);
        centerWindow();

//��ʼ���������С������
        InitFonts();

        setSize(300,120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public void centerWindow(){
//�����ʾ�����洰�ڵĴ�С
        Toolkit tk=getToolkit();
        Dimension dm=tk.getScreenSize();
//�ô��ھ�����ʾ
        setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
    }
    private void InitFonts(){
//���ϵͳ����������
        GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[]  fontList=ge.getAvailableFontFamilyNames();
        int i;

//�����������
        for(i=0;i<fontList.length;i++)
            cbxFont.addItem(String.valueOf(i)+"  |  "+fontList[i]);

        cbxFont.setSelectedIndex(23);//ѡ��indexΪ231����
        cbxFont.addItemListener(this);
//��������С
        for(i=9;i<=72;i++)
            cbxFontSize.addItem(Integer.toString(i));
        cbxFontSize.setSelectedIndex(9);//ѡ��indexΪ3����
        cbxFontSize.addItemListener(this);
    }
    public static void main(String args[]){
        TestJComboBox frm=new TestJComboBox("JFrame with JComboBox");

        frm.setVisible(true);
    }
}