package com.cfhui.es;

import com.cfhui.es.constant.ESConstant;
import com.cfhui.es.document.Order;
import com.cfhui.es.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Optional;

@SpringBootTest
public class RestHighLevelClientTests {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    /**
     * []
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:34
     */
    @Test
    public void test() {

    }
    /**
     * [创建简单索引]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:20
     */
    @Test
    public void test1() throws IOException {
        CreateIndexResponse response = restHighLevelClient.indices().create(new CreateIndexRequest(ESConstant.INDEX_ORDER), RequestOptions.DEFAULT);
        Assertions.assertEquals("hui", response.index());
    }

    /**
     * [自定义mapping创建索引]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:34
     */
    @Test
    public void test5() throws IOException {
        String index = "hui_mapping_index";
        // 创建索引的请求
        CreateIndexRequest indexRequest = new CreateIndexRequest(index);
        // mapping规则去别的地方写好之后，复制粘贴过来，IDEA会自动转义相关符号
        String mapping = "{\n" +
                "    \"properties\": {\n" +
                "      \"name\": {\n" +
                "        \"type\": \"keyword\"\n" +
                "      },\n" +
                "      \"age\": {\n" +
                "        \"type\": \"integer\"\n" +
                "      }\n" +
                "    }\n" +
                "  }";
        // 添加索引的mapping规则
        indexRequest.mapping(mapping,XContentType.JSON);
        // 发送请求
        CreateIndexResponse response = restHighLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        Assertions.assertEquals(index, response.index());

    }


    /**
     * [判断索引是否存在]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:20
     */
    @Test
    public void test2() throws IOException {
        boolean exists = restHighLevelClient.indices().exists(new GetIndexRequest("hui_mapping_index"), RequestOptions.DEFAULT);
        Assert.isTrue(exists, "索引不存在");
    }
    /**
     * [删除索引]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:20
     */
    @Test
    public void test3() throws IOException {
        AcknowledgedResponse response = restHighLevelClient.indices().delete(new DeleteIndexRequest("hui"), RequestOptions.DEFAULT);
        Assertions.assertTrue(response.isAcknowledged());
    }

    /**
     * [文档的创建]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:34
     */
    @Test
    public void test4() throws IOException {
        Order order = Order.builder().id(1).orderNo(1L).orderDesc("test").build();
        IndexRequest indexRequest = new IndexRequest("order");
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(order);
        indexRequest.source(s, XContentType.JSON);
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("index.toString() = " + index.toString());
    }

    /**
     * [根据id获取文档数据]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:34
     */
    @Test
    public void test6() throws IOException {
        GetRequest request = new GetRequest(ESConstant.INDEX_ORDER);
        request.id("1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(request.toString());
    }

    /**
     * [文档更新-全局]
     * [存在更新，不存在新建]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:34
     */
    @Test
    public void test7() throws IOException {
        Order order = Order.builder().id(1).orderDesc("cfhui").build();
        IndexRequest request = new IndexRequest("order");
        request.id("1");
        request.source(objectMapper.writeValueAsString(order), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println("index.toString() = " + response.getResult().toString());
    }

    /**
     * [局部更新-推荐]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:34
     */
    @Test
    public void test8() throws IOException {
        Order order = Order.builder().id(1).orderDesc("cfhui-2").build();
        UpdateRequest request = new UpdateRequest("order", "1");
        request.doc(objectMapper.writeValueAsString(order), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    /**
     * [文档数据的删除]
     * @author cfhui
     * @since V1
     * @date 2023/2/14 下午 2:34
     */
    @Test
    public void test9() throws IOException {
        DeleteRequest request = new DeleteRequest(ESConstant.INDEX_ORDER, "1");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }




}
