package com.cfhui;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Stopwatch;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GuavaTest
 * @Description TODO
 * @Author cfhui
 * @Date 2022/12/11 9:19
 */
public class GuavaTest {

    @Test
    public void checkNull() {
        String a = null;
        String s = Preconditions.checkNotNull(a, "a不能为null");
    }

    @Test
    public void stringSplit() {
        String str = "hello, world, your, name";
        Splitter sp = Splitter.on(",").trimResults();
        Iterable<String> split = sp.split(str);
        for (String s : split) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void pageTest() {
        List<String> list = Arrays.asList("1","2","3","4","5","6","7");
        List<List<String>> partition = Lists.partition(list, 2);  // this
        System.out.println("partition = " + partition);
    }

    @Test
    public void fileTest() throws IOException {
        String path = "doc.txt";
        URL url = this.getClass().getClassLoader().getResource(path);
        File file = new File(url.getPath());
        String fileExtension = Files.getFileExtension(path);
        Assert.assertEquals("txt", fileExtension);
        boolean isFile = Files.isFile().apply(file);
        Assert.assertTrue(isFile);

        String nameWithoutExtension = Files.getNameWithoutExtension(path);
        Assert.assertEquals("doc", nameWithoutExtension);

        List<String> strings = Files.readLines(file, Charsets.UTF_8);
        strings.forEach(System.out::println);

        Files.copy(file, new File("doc_copy.txt"));

//        Files.move();
    }

    @Test
    public void cacheTest() {

    }

    @Test
    public void stopWatchTest() throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Thread.sleep(1000);
        System.out.println(stopwatch.stop());

        stopwatch.start();
        Thread.sleep(2000);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
        Thread.sleep(3000);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
        Thread.sleep(1000);
        System.out.println(stopwatch.stop());
    }

    @Test
    public void testCache() throws ExecutionException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
            .initialCapacity(5)
            .maximumSize(10)
            .expireAfterWrite(20, TimeUnit.SECONDS)
            .build();
        String key1 = "key1";
        cache.put(key1, "value1");
        String s = cache.get(key1, new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 本地获取不到访问数据库获取
                return getValuefromDB(key1);
            }

            private String getValuefromDB(String key1) {
                return "value1";
            }
        });
        Assert.assertEquals("value1", s);
        System.out.println(s);
        // 删除key
        cache.invalidate(key1);

    }
}
