package ws;

import ofile.Choosefile;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class WriteDirectlib {

    public  static void deleteAllIndex(String path) throws Exception {
        Directory directory = FSDirectory.open(new File("D:\\project\\director").toPath());

        IndexWriter indexWriter=new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));
        //删除全部索引，每次新增文件重新创建引索库时都调用
        indexWriter.deleteAll();
        //关闭indexwriter
        indexWriter.close();
    }

    public static void writedirec(String path)throws Exception{
        //考虑到文件嵌套，所以先将文件中数据读入内存来进行速度的优化
        RAMDirectory rd=new RAMDirectory();
        IndexWriter iw=new IndexWriter(rd,new IndexWriterConfig(new IKAnalyzer()));


        File dir=new File(path);
        File[] files=dir.listFiles();
        for (File f:files){
            //递归解决嵌套文件
            if(f.isDirectory()) writedirec(f.getPath());//运用一个递归操作来解决文件夹问题
            //取文件名
            else{
            String fileName= f.getName();
            //文件的路径
            String filePath=f.getPath();
            //文件的内容
            String fileContext= Choosefile.readcontext(filePath);
            //文件的大小
            long fileSize=FileUtils.sizeOf(f);
            //创建Filed
            //参数1:域的名称 参数2:域的内容 参数3:是否存储
            Field fieldName=new TextField("name",fileName,Field.Store.YES);
            Field fieldPath=new TextField("path",filePath,Field.Store.YES);
            Field fieldContext=new TextField("context",fileContext,Field.Store.YES);
            Field fieldSize=new TextField("size",fileSize+"",Field.Store.YES);
            //创建文档对象
            Document document=new Document();
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContext);
            document.add(fieldSize);
            //把文档对象写入内存
            iw.addDocument(document);
            }
        }
        //内存关闭
        iw.close();
        //创建一个Director对象指定索引库的位置
        Directory directory = FSDirectory.open(new File("D:\\project\\director").toPath());
        //创建一个IndexWrite对象
        IndexWriter indexWriter=new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));
        //读取内存上的文件,对应每个文件来创建一个文档对象
        indexWriter.addIndexes(new Directory[] {rd});
        //关闭IndexWrite对象
        indexWriter.close();
    }
    public static void main(String args[])throws Exception{
        deleteAllIndex("D:\\project\\doc");
        writedirec("D:\\project\\doc");
    }

}
