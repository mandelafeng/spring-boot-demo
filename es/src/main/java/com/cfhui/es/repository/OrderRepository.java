package com.cfhui.es.repository;

import com.cfhui.es.document.Order;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrderRepository extends ElasticsearchRepository<Order, Integer> {
}
