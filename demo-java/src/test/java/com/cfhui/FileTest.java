package com.cfhui;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FileTest
 * @Description TODO
 * @Author cfhui
 * @Date 2022/12/11 9:05
 */
@Slf4j
public class FileTest {

    @Test
    public void getCurrentDirPath() {
        log.info(System.getProperty("user.dir"));
    }
    @Test
    public void ss() {
        System.out.println("ss");
    }

    @Test
    public void testPDF() throws Exception{
        PDDocument document = PDDocument.load(new File("C:\\Users\\di\\Desktop\\综合评估报告 (19).pdf"));
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String text = pdfTextStripper.getText(document);
        System.out.println(text);
        List<String> strings = Splitter.on("\r\n").splitToList(text);
        System.out.println(strings);
        int start = strings.indexOf("场景名称 实际用时 仿真时长 测试里程 所用的算法 评分 评价");
        int end = strings.indexOf("分类指标综合评价");
        Map<String, String> map = new HashMap<>();
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < strings.size(); i++) {
            if (i > start && i < end) {
                String item = strings.get(i);
                if (item.contains("通过")) {
                    List<String> strings1 = Splitter.on(" ").splitToList(item);
                    String res = strings1.get(strings1.size() - 1);
                    map.put(path.toString(), res);
                    path = new StringBuilder();
                } else {
                    path.append(item);
                }
            }
        }System.out.println(map);
    }
}
