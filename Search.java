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

/*查询索引库的步骤*/
public class Search {
    public static ArrayList<String> search(String s) throws Exception {
        //创建一个Directory对象，保存在指定位置
        FSDirectory direcotory= FSDirectory.open(new File("D:\\project\\director").toPath());
        //创建IndexReader
        IndexReader indexReader= DirectoryReader.open(direcotory);
        IndexSearcher indexSearcher=new IndexSearcher(indexReader);
        //封装一个查询对象
        IKAnalyzer analyzer=new IKAnalyzer();
        QueryParser queryParser = new QueryParser("context",analyzer);
        Query query = queryParser.parse(s);


        //封装查询出来的所有的document对象的集合
        TopDocs topDocs=indexSearcher.search(query,3);//符合条件前三条
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;//获取文档编号id的集合
        ArrayList<String> D=new ArrayList<>();
        ArrayList<String> Dcontext=new ArrayList<>();
        //获取对应的文档对象
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
