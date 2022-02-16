package ofile;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class Readtxt  {
    public static String gettxt(String path)throws Exception{
        String result;
        result= FileUtils.readFileToString(new File(path),"GB2312");
            return result;


    }

}
