package com.cfhui;


import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.FSDirectory;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/8/30 上午 9:55
 */
public class ChineseSearch {

    public static List<String> search(String indexDir, String q) throws Exception {
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        QueryParser parser = new QueryParser("desc", analyzer);
        Query query = parser.parse(q);
        TopDocs docs = searcher.search(query, 10);
        System.out.println("查询到" + docs.totalHits + "条记录");

        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color=red>", "</font></b>");
        QueryScorer scorer = new QueryScorer(query);
        SimpleSpanFragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);

        List<String> list = new ArrayList<>();
        for(ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println("city: " + doc.get("city"));
            System.out.println("desc: " + doc.get("desc"));
            String desc = doc.get("desc");

            if (desc != null) {
                TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(desc));
                String summary = highlighter.getBestFragment(tokenStream, desc);
                System.out.println("高亮后的desc:" + summary);
                list.add(summary);
            }
        }
        reader.close();
        return list;
    }

}
