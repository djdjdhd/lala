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
        //ɾ��ȫ��������ÿ�������ļ����´���������ʱ������
        indexWriter.deleteAll();
        //�ر�indexwriter
        indexWriter.close();
    }

    public static void writedirec(String path)throws Exception{
        //���ǵ��ļ�Ƕ�ף������Ƚ��ļ������ݶ����ڴ��������ٶȵ��Ż�
        RAMDirectory rd=new RAMDirectory();
        IndexWriter iw=new IndexWriter(rd,new IndexWriterConfig(new IKAnalyzer()));


        File dir=new File(path);
        File[] files=dir.listFiles();
        for (File f:files){
            //�ݹ���Ƕ���ļ�
            if(f.isDirectory()) writedirec(f.getPath());//����һ���ݹ����������ļ�������
            //ȡ�ļ���
            else{
            String fileName= f.getName();
            //�ļ���·��
            String filePath=f.getPath();
            //�ļ�������
            String fileContext= Choosefile.readcontext(filePath);
            //�ļ��Ĵ�С
            long fileSize=FileUtils.sizeOf(f);
            //����Filed
            //����1:������� ����2:������� ����3:�Ƿ�洢
            Field fieldName=new TextField("name",fileName,Field.Store.YES);
            Field fieldPath=new TextField("path",filePath,Field.Store.YES);
            Field fieldContext=new TextField("context",fileContext,Field.Store.YES);
            Field fieldSize=new TextField("size",fileSize+"",Field.Store.YES);
            //�����ĵ�����
            Document document=new Document();
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContext);
            document.add(fieldSize);
            //���ĵ�����д���ڴ�
            iw.addDocument(document);
            }
        }
        //�ڴ�ر�
        iw.close();
        //����һ��Director����ָ���������λ��
        Directory directory = FSDirectory.open(new File("D:\\project\\director").toPath());
        //����һ��IndexWrite����
        IndexWriter indexWriter=new IndexWriter(directory,new IndexWriterConfig(new IKAnalyzer()));
        //��ȡ�ڴ��ϵ��ļ�,��Ӧÿ���ļ�������һ���ĵ�����
        indexWriter.addIndexes(new Directory[] {rd});
        //�ر�IndexWrite����
        indexWriter.close();
    }
    public static void main(String args[])throws Exception{
        deleteAllIndex("D:\\project\\doc");
        writedirec("D:\\project\\doc");
    }

}
