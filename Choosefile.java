package ofile;

public class Choosefile {//��ͬ���͵��ļ�Ӧ�ò�ͬ�ķ������룬�ø���������ɸѡ
    public static String readcontext(String path)throws Exception{

        if(path.endsWith(".txt")) {
            return Readtxt.gettxt(path);
        }
        else if(path.endsWith(".doc")||path.endsWith(".docx")){
            return Readword.getword(path);
        }
        else if(path.endsWith(".pdf")){
            return Readpdf.getpdf(path);
        }
        else if(path.endsWith(".pptx")||path.endsWith(".ppt")){
            return Readppt.getppt(path);
        }
        else if(path.endsWith(".xlsx")||path.endsWith(".xls")){
            return Readexcel.getexcel(path);
        }
        else {
           return "";

        }
    }


}