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
    String xmlPath = "E:\\MyWorkspace\\Data\\msme\\OpenSCENARIO_v0.9.1_examples\\OpenSCENARIO_v0.9.1\\Examples\\DE\\Langsamer_Vorausfahrer\\Langsamer_Vorausfahrer.xosc";
    String xsdPath = "E:\\MyWorkspace\\Data\\msme\\OpenSCENARIO_v0.9.1_specification\\OpenSCENARIO_v0.9.1\\OpenSCENARIO_v0.9.1.xsd";
    @Test
    public void test1() {

        boolean isOK = XMLValidateUtils.validateXMLByXSD(xmlPath, xsdPath);
        Assert.assertTrue(isOK);
    }
    @Test
    public void test2() {
        boolean b = XMLValidateUtils.validateXMLSchema(xsdPath, xmlPath);
        Assert.assertTrue(b);
    }

}
