package ofile;

import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.sl.usermodel.Slide;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.xslf.usermodel.*;

import java.io.*;
import java.util.List;

public class Readppt {
    public static String getppt(String path)throws Exception{

        StringBuilder content=new StringBuilder();


            SlideShow slideShow=null;

            if(path.endsWith(".ppt")) {
                slideShow=new HSLFSlideShow(new FileInputStream(new File(path)));
            }
            else if(path.endsWith(".pptx")){
                slideShow=new XMLSlideShow(new FileInputStream(new File(path)));
            }
            if(slideShow==null) {
                return "";
            }
            List<Slide> slides=slideShow.getSlides();
            for(Slide slide: slides){
                List shapes=slide.getShapes();
                if(shapes!=null){
                    for(Object shape:shapes){

                        if(shape==null) {
                            continue;
                        }
                        if (shape instanceof HSLFTextShape) {// 文本框
                            String text = ((HSLFTextShape) shape).getText();

                            content.append(text);
                        }
                        if (shape instanceof XSLFTextShape) {// 文本框
                            String text = ((XSLFTextShape) shape).getText();

                            content.append(text);
                        }
                    }
                }
            }

        return content.toString();
    }

    public static void main(String[] args)throws Exception{
        String t;
        t=getppt("D:\\project\\doc\\ppppp.pptx");
        System.out.println(t);
    }
}
