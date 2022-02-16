package ws;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;

/*��ѯ������Ĳ���*/
public class Search {
    public static ArrayList<String> search(String s) throws Exception {
        //����һ��Directory���󣬱�����ָ��λ��
        FSDirectory direcotory= FSDirectory.open(new File("D:\\project\\director").toPath());
        //����IndexReader
        IndexReader indexReader= DirectoryReader.open(direcotory);
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        //��װһ����ѯ����
        IKAnalyzer analyzer=new IKAnalyzer();
        QueryParser queryParser = new QueryParser("context",analyzer);
        Query query = queryParser.parse(s);


        //��װ��ѯ���������е�document����ļ���
        TopDocs topDocs=indexSearcher.search(query,3);//��������ǰ����
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;//��ȡ�ĵ����id�ļ���
        ArrayList<String> D=new ArrayList<>();
        ArrayList<String> Dcontext=new ArrayList<>();
        //��ȡ��Ӧ���ĵ�����
        for (ScoreDoc scoreDoc : scoreDocs) {
            Document document=indexSearcher.doc(scoreDoc.doc);
            D.add(document.get("path"));
            String text=document.get("context");
            Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
            Highlighter highlighter = new Highlighter(formatter,new QueryScorer(query));
            highlighter.setTextFragmenter(new SimpleFragmenter(text.length()));

            if (text != null) {
                TokenStream tokenStream = analyzer.tokenStream("context",new StringReader(text));
                String highLightText = highlighter.getBestFragment(tokenStream, text);
                System.out.println(highLightText);
                D.add(highLightText);
            }
        }
        indexReader.close();
        return D;
    }
}
