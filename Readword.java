package ofile;

import com.spire.doc.Document;

public class Readword {
    public static String getword(String path){
        Document doc = new Document();
        doc.loadFromFile(path);
        //��ȡ�ı�����ΪString
        String result = doc.getText();
        return result;
    }
//    public static void main(String[] args) {
//        System.out.println(getword("D:\\project\\doc\\SortMain��˵��.doc"));
//    }

}
