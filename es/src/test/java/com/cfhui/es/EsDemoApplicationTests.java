package com.cfhui.es;

import com.cfhui.es.document.Order;
import com.cfhui.es.repository.OrderRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class EsDemoApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    OrderRepository repo;

    @Test
    public void saveDocument() throws IOException {
        // 构建索引请求 传入参数为：index名，type名，自定义该Document的_id
        IndexRequest indexRequest = new IndexRequest("postilhub", "user", "1");

        // 传入参数为：新增Document的数据，数据类型
        indexRequest.source("{\"id\":\"1\",\"username\":\"小明\",\"age\":19}", XContentType.JSON);

        // 执行新增 RequestOptions.DEFAULT为枚举类型，默认即可
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        // 查看操作是否成功
        System.out.println(indexResponse.status());
    }


    @Test
    void contextLoads() {
//        Order order = new Order();
//        order.setId(1);
//        order.setUsername("hui");
//        Order save = repo.save(order);
//        System.out.println("save = " + save);
    }

    @Test
    void test2() {
//        Order order = new Order();
//        order.setId(1);
//        order.setUsername("hui");
//        Order save = repo.save(order);
//        System.out.println("save = " + save);
//        Optional<Order> byId = repo.findById(1);
//        if (byId.isPresent()) {
//            Order order1 = byId.get();
//            System.out.println("order1 = " + order1);
//        }
//        List<Order> list = new ArrayList<>();
//        List<Integer> ids = Arrays.asList(1, 2, 3);
//        repo.save(order);
//        repo.saveAll(list);
//        repo.findById(1);
//        repo.findAll();
//        repo.findAll(Sort.by(Sort.Order.desc("orderCount")));
//        repo.findAllById(ids);
//        repo.count();
//        repo.delete(order);
//        repo.deleteAll();
//        repo.existsById(1);
//        repo.searchSimilar();

    }
    @Test
    public void test3() throws IOException {
        boolean exists = restHighLevelClient.indices().exists(new GetIndexRequest("order"), RequestOptions.DEFAULT);
        Assert.isTrue(exists, "索引存在");
    }

}
