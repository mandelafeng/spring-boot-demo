package com.cfhui;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2024/8/29 下午 7:35
 */
public class Indexer {
    private IndexWriter writer;
    public Indexer(String indexDir) throws Exception{
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(dir, config);
    }


    public int indexAll(String dataDir) throws Exception{
        File[] files = new File(dataDir).listFiles();
        if (null != files) {
            for (File file : files) {
                indexFile(file);
            }
        }
        writer.commit();
        return writer.numDocs();
    }

    private void indexFile(File file) throws Exception{
        Document doc = getDocument(file);
        writer.addDocument(doc);
    }

    private Document getDocument(File file) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("contents", new FileReader(file)));
        doc.add(new TextField("fileName", file.getName(), Field.Store.YES));
        doc.add(new TextField("fullPath", file.getCanonicalPath(), Field.Store.YES));
        return doc;
    }
}
