package ui.bag;

import ui.searchUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestJFrameExtends extends JFrame implements ActionListener{
    static JMenuBar	  mb=new JMenuBar();//菜单栏
           JToolBar mtb=new JToolBar();
    static FgMenu	  mFile=new FgMenu("文件(F)",KeyEvent.VK_F);//"文件"菜单
    static JMenuItem miNew=new JMenuItem("新建(N)",KeyEvent.VK_N),
            miOpen=new JMenuItem("打开(O)...",KeyEvent.VK_O),
            miSave=new JMenuItem("保存(S)",KeyEvent.VK_S),
            miFont=new JMenuItem("字体与颜色(F)...",KeyEvent.VK_F),
            miQuit=new JMenuItem("退出(X)",KeyEvent.VK_X);
    public static JEditorPane ta=new JEditorPane();//文本框

            TestJComboBox frm=new TestJComboBox("JFrame with JComboBox");
    FgButton[] btn={new FgButton(new ImageIcon (ImageScaling.zoom("img/new.png",0.1)),
            "新建文件"),
            new FgButton(new ImageIcon(ImageScaling.zoom("img/open.png",0.1)),
                    "打开文件"),
            new FgButton(new ImageIcon (ImageScaling.zoom("img/save.png",0.1)),
                    "高亮并保存或保存文件"),
            new FgButton(new ImageIcon (ImageScaling.zoom("img/22.jfif",0.05)),
                    "搜索文件")
    };
    JFileChooser fd=new JFileChooser();

    ui.searchUI sui=new searchUI("搜索文本内容");
    /////////////////////////////////////////////////







    /////////////////////////



    TestJFrameExtends(String sTitle){
        super(sTitle);

        addToolBar();
//②：添加组件。本例直接添加菜单与JTextArea
       addMenus();
//添加带滚动条(JScrollPane)的文本编辑框JTextArea
        JScrollPane sp=new JScrollPane(ta);
        add(sp);
//③：设置窗口大小
        setSize(800, 600);
//设置close按钮的操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//使窗口在显示屏居中显示
        centerWindow();
//改变窗口图标
        Toolkit tk=getToolkit(); //得到一个Toolkit对象
        Image icon=tk.getImage("img/new.png"); //获取图标
        setIconImage(icon);

    }
    private void addToolBar(){
//工具条
        Container c=getContentPane();
        c.add(BorderLayout.NORTH, mtb);

        mtb.setLayout(new FlowLayout(FlowLayout.LEFT));


        for(int i=0;i<btn.length;i++){
            btn[i].setBorder(BorderFactory.createEmptyBorder());
            btn[i].addActionListener(this);
            mtb.add(btn[i]);
        }
//设置不可浮动
        mtb.setFloatable(false);
    }
    public void actionPerformed(ActionEvent e){

            String ItemName = e.getActionCommand();
            if(e.getSource()==miQuit) System.exit(0);
            if(e.getSource()==miFont) frm.setVisible(true);
            if(e.getSource()==miSave ||btn[0]==e.getSource()) ta.setText("");
            if (e.getSource()==miOpen||e.getSource()==btn[1]){//点击了打开按钮
                fd.showOpenDialog(null);
                File f=fd.getSelectedFile();
                readFile(f);
            }
            if (e.getSource()==miSave||e.getSource()==btn[2]){//点击了保存按钮
                fd.showSaveDialog(null);
                File f=fd.getSelectedFile();
                saveFile(f);
            }
        if (e.getSource()==btn[3]){
            sui.setVisible(true);
        }
        }

    private void addMenus(){
        setJMenuBar(mb);
        mFile.add(miNew);//新建
        miNew.addActionListener(this);

        mFile.add(miOpen);//打开
        miOpen.addActionListener(this);

        mFile.add(miSave);//保存
        miSave.addActionListener(this);

        mFile.addSeparator();//分割条
        mFile.add(miFont);//字体菜单
        miFont.addActionListener(this);

        mFile.addSeparator();//分割条
        mFile.add(miQuit);//退出
        miQuit.addActionListener(this);

        mb.add(mFile); //将"文件"菜单添加到菜单栏上


//③：设置窗口位置和大小

//设置close按钮的操作

//④：显示窗口


    }
    private void readFile(File file) {
        if (file == null) {

            ta.setText("");
            return;
        }
        try{
            ta.setText(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    //保存
    private void saveFile(File file) {
        BufferedWriter bufw;
        try {
            bufw = new BufferedWriter(new FileWriter(file));
            String str = ta.getText();
            bufw.write(str);
            bufw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //窗口居中
    public void centerWindow(){
//获得显示屏桌面窗口的大小
        Toolkit tk=getToolkit();
        Dimension dm=tk.getScreenSize();
//让窗口居中显示
        setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
    }

    /////////////////////////////////////////////////////////


    public static void main(String args[]){
//①：创建窗口对象
        TestJFrameExtends frm=new TestJFrameExtends ("搜索记事本");
//④：显示窗口
        frm.setVisible(true);
    }



}


//////////////////////////////////////////////////////////////////
class FgButton extends JButton{
    public FgButton(){
        super();
    }
    public FgButton(Icon icon){
        super(icon);
    }
    public FgButton(Icon icon,String strToolTipText){
        super(icon);
        setToolTipText(strToolTipText);
    }
    public FgButton(String text){
        super(text);
    }
    public FgButton(String text, Icon icon, String strToolTipText){
        super(text, icon);
        setToolTipText(strToolTipText);
    }
}

class FgMenu extends JMenu{
    public FgMenu(String label){
        super(label);
    }
    public FgMenu(String label,int nAccelerator){
        super(label);
        setMnemonic(nAccelerator);
    }
}


