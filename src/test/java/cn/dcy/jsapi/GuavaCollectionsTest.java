package cn.dcy.jsapi;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.*;
import com.google.common.collect.*;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Kyle
 * @date 2024/06/15
 */
@SpringBootTest
@Slf4j
public class GuavaCollectionsTest {

    @Test
    void test() {
        List<String> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet();
        Map<String, String> map = Maps.newHashMap();

        list.add("one");
        list.add("two");
        set.add("three");
        set.add("four");
        map.put("five", "5");
        map.put("six", "6");

        System.out.println(list);
        System.out.println(set);
        System.out.println(map);
    }

    @Test
    void immutable() {
        ImmutableList<String> immutableList = ImmutableList.of("one", "two", "three");
        ImmutableSet<String> immutableSet = ImmutableSet.of("four", "five", "six");
        ImmutableMap<String, String> immutableMap = ImmutableMap.of("seven", "7", "eight", "8");

        System.out.println(immutableList);
        System.out.println(immutableSet);
        System.out.println(immutableMap);
    }

    @Test
    void cache() throws Exception {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                // 最大存储条数，缓存将尝试逐出最近或不经常使用的条目
                .maximumSize(10000)
                // 可以设定删除时候的权重判断
                //.weigher((Weigher<String, String>) (x, y) -> x.length() - y.length())
                // 有效时间
                .expireAfterWrite(5, TimeUnit.SECONDS)
                // 记录次数
                .recordStats()
                .build();

        cache.put("xfg", "bugstack.cn");
        log.info("测试结果：{}", cache.getIfPresent("xfg"));
        //cache.invalidate("xfg");
        Thread.sleep(3000);
        // cache.invalidateAll(); 也可以全部删除
        log.info("测试结果：{}", cache.getIfPresent("xfg"));
        Thread.sleep(2000);
        log.info("测试结果：{}", cache.getIfPresent("xfg"));
        log.info("测试结果：{}", cache.stats());

        //LoadingCache<String, String> cache = CacheBuilder.newBuilder()
        //        .maximumSize(100) // 最大缓存100个条目
        //        .expireAfterWrite(3, TimeUnit.SECONDS)
        //        .build(new CacheLoader<String, String>() {
        //            @Override
        //            public String load(String key) {
        //                return "Value for " + key; // 当缓存没有命中时加载数据的方式
        //            }
        //        });
        //cache.put("key1", "value1");
        //cache.put("key2", "value2");
        //System.out.println(cache.get("key1")); // 输出 "Value for key1"
        ////Thread.sleep(4000);
        //System.out.println(cache.get("key2")); // 输出 "Value for key2"
    }

    @Test
    void stringUtil() {
        // Splitter
        String input = "one,two,,three,,four";
        List<String> result = Splitter.on(',')
                .omitEmptyStrings()
                .trimResults()
                .splitToList(input);
        System.out.println(result); // 输出 [one, two, three, four]

        // Joiner
        List<String> list = List.of("one", "two", "three");
        String joined = Joiner.on(", ").join(list);
        System.out.println(joined); // 输出 "one, two, three"
    }

    @Test
    void precondition() {
        int a = 10;
        int b = -1;

        // 校验参数
        Preconditions.checkArgument(a > 0, "Argument was %s but expected positive", a);
        // 这行会抛出异常
        Preconditions.checkArgument(b > 0, "Argument was %s but expected positive", b);
    }

    @Test
    public void test_eventbus() {
        //AsyncEventBus eventBus = new AsyncEventBus(Executors.newCachedThreadPool());
        EventBus eventBus = new EventBus();
        eventBus.register(new Listener());
        eventBus.register(new Listener2());
        // 可以由其他服务推送消息，之后就可以在监听中收到了
        eventBus.post("消息总线，订单号：100001");
    }
    static class Listener {
        @Subscribe
        public void handleEvent(String orderId) {
            log.info("测试结果：{}", orderId);
        }
    }
    static class Listener2 {
        @Subscribe
        public void handleEvent(String orderId) {
            log.info("测试结果2：{}", orderId);
        }
    }
}
