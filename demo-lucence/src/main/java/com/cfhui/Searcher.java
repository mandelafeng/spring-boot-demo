package com.cfhui;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/8/30 上午 9:31
 */
public class Searcher {

    public static void search(String indexDir, String query) throws Exception {
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        StandardAnalyzer analyzer = new StandardAnalyzer();
        QueryParser parser = new QueryParser("contents", analyzer);
        Query parse = parser.parse(query);
        TopDocs docs = searcher.search(parse, 10);
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.get("fullPath"));
        }
        reader.close();
    }

    public static void main(String[] args) {
        String indexDir = "E:\\lucene";
        String query = "security";
        try {
            Searcher.search(indexDir, query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
