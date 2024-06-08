package cn.dcy.jsapi;

import cn.dcy.jsapi.domain.Product;
import cn.dcy.jsapi.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class RedisTest {

    @Autowired
    private RedisUtil redisUtil;

    String productsData = "[{\n" +
            "    id: \"e43638ce-6aa0-4b85-b27f-e1d07eb678c6\",\n" +
            "    image: \"images/products/athletic-cotton-socks-6-pairs.jpg\",\n" +
            "    name: \"Black and Gray Athletic Cotton Socks - 6 Pairs\",\n" +
            "    rating: {\n" +
            "      stars: 4.5,\n" +
            "      count: 87\n" +
            "    },\n" +
            "    priceCents: 1090,\n" +
            "    keywords: [\n" +
            "      \"socks\",\n" +
            "      \"sports\",\n" +
            "      \"apparel\"\n" +
            "    ]\n" +
            "  }]";

    @Test
    void insertData() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productsData, Product.class);
        System.out.println(product);
        //redisUtil.set("productsData", product);
    }

}
