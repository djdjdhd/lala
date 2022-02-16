package ui.bag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TestJComboBox extends JFrame implements ItemListener {
    //字体与大小下拉框
    JComboBox cbxFont=new JComboBox();
    JComboBox cbxFontSize=new JComboBox();//字体大小

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

        c.add(new JLabel("字体名称："));
        c.add(cbxFont);
        c.add(new JLabel("字体大小："));
        c.add(cbxFontSize);
        centerWindow();

//初始化字体与大小下拉框
        InitFonts();

        setSize(300,120);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public void centerWindow(){
//获得显示屏桌面窗口的大小
        Toolkit tk=getToolkit();
        Dimension dm=tk.getScreenSize();
//让窗口居中显示
        setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
    }
    private void InitFonts(){
//获得系统的字体数组
        GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[]  fontList=ge.getAvailableFontFamilyNames();
        int i;

//添加字体名称
        for(i=0;i<fontList.length;i++)
            cbxFont.addItem(String.valueOf(i)+"  |  "+fontList[i]);

        cbxFont.setSelectedIndex(23);//选择index为231的项
        cbxFont.addItemListener(this);
//添加字体大小
        for(i=9;i<=72;i++)
            cbxFontSize.addItem(Integer.toString(i));
        cbxFontSize.setSelectedIndex(9);//选择index为3的项
        cbxFontSize.addItemListener(this);
    }
    public static void main(String args[]){
        TestJComboBox frm=new TestJComboBox("JFrame with JComboBox");

        frm.setVisible(true);
    }
}