package ofile;

import com.spire.doc.Document;

public class Readword {
    public static String getword(String path){
        Document doc = new Document();
        doc.loadFromFile(path);
        //获取文本保存为String
        String result = doc.getText();
        return result;
    }
//    public static void main(String[] args) {
//        System.out.println(getword("D:\\project\\doc\\SortMain的说明.doc"));
//    }

}
