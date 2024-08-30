package com.cfhui;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String indexDir = "E:\\lucene";
        String dataDir = "E:\\lucene\\data";
        Indexer indexer = null;
        int indexedNum = 0;
        try {
            indexer = new Indexer(indexDir);
            indexedNum = indexer.indexAll(dataDir);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (indexer != null) {
                    //indexer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("共索引了" + indexedNum + "个文件");
    }
}
