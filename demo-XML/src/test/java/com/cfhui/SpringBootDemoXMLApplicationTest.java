package com.cfhui;


import com.cfhui.util.XMLValidateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName SpringBootDemoXMLApplicationTest
 * @Description TODO
 * @Author cfhui
 * @Date 2022/12/10 11:36
 */
@SpringBootTest
public class SpringBootDemoXMLApplicationTest {
    @Test
    public void test1() {
        String xmlPath = "D:\\My_WorkSpace\\Code\\MyCode\\spring-boot-demo\\demo-XML\\src\\main\\resources\\book.XML";
        String xsdPath = "D:\\My_WorkSpace\\Code\\MyCode\\spring-boot-demo\\demo-XML\\src\\main\\resources\\book.xsd";
        boolean isOK = XMLValidateUtils.validateXMLByXSD(xmlPath, xsdPath);
        Assert.assertTrue(isOK);
    }

}
