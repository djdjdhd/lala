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
    static JMenuBar	  mb=new JMenuBar();//�˵���
           JToolBar mtb=new JToolBar();
    static FgMenu	  mFile=new FgMenu("�ļ�(F)",KeyEvent.VK_F);//"�ļ�"�˵�
    static JMenuItem miNew=new JMenuItem("�½�(N)",KeyEvent.VK_N),
            miOpen=new JMenuItem("��(O)...",KeyEvent.VK_O),
            miSave=new JMenuItem("����(S)",KeyEvent.VK_S),
            miFont=new JMenuItem("��������ɫ(F)...",KeyEvent.VK_F),
            miQuit=new JMenuItem("�˳�(X)",KeyEvent.VK_X);
    public static JEditorPane ta=new JEditorPane();//�ı���

            TestJComboBox frm=new TestJComboBox("JFrame with JComboBox");
    FgButton[] btn={new FgButton(new ImageIcon (ImageScaling.zoom("img/new.png",0.1)),
            "�½��ļ�"),
            new FgButton(new ImageIcon(ImageScaling.zoom("img/open.png",0.1)),
                    "���ļ�"),
            new FgButton(new ImageIcon (ImageScaling.zoom("img/save.png",0.1)),
                    "����������򱣴��ļ�"),
            new FgButton(new ImageIcon (ImageScaling.zoom("img/22.jfif",0.05)),
                    "�����ļ�")
    };
    JFileChooser fd=new JFileChooser();

    ui.searchUI sui=new searchUI("�����ı�����");
    /////////////////////////////////////////////////







    /////////////////////////



    TestJFrameExtends(String sTitle){
        super(sTitle);

        addToolBar();
//�ڣ�������������ֱ����Ӳ˵���JTextArea
       addMenus();
//��Ӵ�������(JScrollPane)���ı��༭��JTextArea
        JScrollPane sp=new JScrollPane(ta);
        add(sp);
//�ۣ����ô��ڴ�С
        setSize(800, 600);
//����close��ť�Ĳ���
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//ʹ��������ʾ��������ʾ
        centerWindow();
//�ı䴰��ͼ��
        Toolkit tk=getToolkit(); //�õ�һ��Toolkit����
        Image icon=tk.getImage("img/new.png"); //��ȡͼ��
        setIconImage(icon);

    }
    private void addToolBar(){
//������
        Container c=getContentPane();
        c.add(BorderLayout.NORTH, mtb);

        mtb.setLayout(new FlowLayout(FlowLayout.LEFT));


        for(int i=0;i<btn.length;i++){
            btn[i].setBorder(BorderFactory.createEmptyBorder());
            btn[i].addActionListener(this);
            mtb.add(btn[i]);
        }
//���ò��ɸ���
        mtb.setFloatable(false);
    }
    public void actionPerformed(ActionEvent e){

            String ItemName = e.getActionCommand();
            if(e.getSource()==miQuit) System.exit(0);
            if(e.getSource()==miFont) frm.setVisible(true);
            if(e.getSource()==miSave ||btn[0]==e.getSource()) ta.setText("");
            if (e.getSource()==miOpen||e.getSource()==btn[1]){//����˴򿪰�ť
                fd.showOpenDialog(null);
                File f=fd.getSelectedFile();
                readFile(f);
            }
            if (e.getSource()==miSave||e.getSource()==btn[2]){//����˱��水ť
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
        mFile.add(miNew);//�½�
        miNew.addActionListener(this);

        mFile.add(miOpen);//��
        miOpen.addActionListener(this);

        mFile.add(miSave);//����
        miSave.addActionListener(this);

        mFile.addSeparator();//�ָ���
        mFile.add(miFont);//����˵�
        miFont.addActionListener(this);

        mFile.addSeparator();//�ָ���
        mFile.add(miQuit);//�˳�
        miQuit.addActionListener(this);

        mb.add(mFile); //��"�ļ�"�˵���ӵ��˵�����


//�ۣ����ô���λ�úʹ�С

//����close��ť�Ĳ���

//�ܣ���ʾ����


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
    //����
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
    //���ھ���
    public void centerWindow(){
//�����ʾ�����洰�ڵĴ�С
        Toolkit tk=getToolkit();
        Dimension dm=tk.getScreenSize();
//�ô��ھ�����ʾ
        setLocation((int)(dm.getWidth()-getWidth())/2,(int)(dm.getHeight()-getHeight())/2);
    }

    /////////////////////////////////////////////////////////


    public static void main(String args[]){
//�٣��������ڶ���
        TestJFrameExtends frm=new TestJFrameExtends ("�������±�");
//�ܣ���ʾ����
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


