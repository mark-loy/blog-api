package com.markloy.markblog;

import com.markloy.markblog.mapper.ArticleMapper;
import com.markloy.markblog.pojo.Article;
import com.markloy.markblog.pojo.ArticleExample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class MarkblogApplicationTests {

    @Test
    void contextLoads() {

        String title = "linux  spring   命令 4";
        String replace = title.replace(" ", "|");
        System.out.println(replace);
        String[] split = replace.split("|");
        System.out.println(Arrays.toString(split));

        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            if (list.isEmpty()) {
                list.add(s);
            } else {
                int size = list.size();
                if ( list.get( size - 1).equals("|")) {
                    if (!"|".equals(s)) {
                        list.add(s);
                    }
                } else {
                    if (!"|".equals(s)) {
                        list.add(s);
                    } else {
                        list.add("|");
                    }
                }
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
        StringBuilder search = new StringBuilder();
        for (String item : list) {
            search.append(item);
        }
        System.out.println(search.toString());
    }

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据时间倒序获取文章信息，处理返回结果
     * @return
     */
    @Test
     void getTimeLineData() {
        //按照时间倒序查询文章信息
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("gmt_create desc");
        List<Article> articles = articleMapper.selectByExample(articleExample);
        AtomicInteger year = new AtomicInteger();
        // 文章集合
        List<Map<String, Object>> articleList = new ArrayList<>();
        // 时间线集合
        List<Map<String, Object>> timeList = new ArrayList<>();
        // 记录循环次数
        for (int i = 0; i < articles.size(); i++) {
            Map<String, Object> yearMap = new HashMap<>();
            Map<String, Object> articleMap = new HashMap<>();
            Date date = new Date(Long.parseLong(articles.get(i).getGmtCreate() + "000"));
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            // 获取年份
            int currentYear = instance.get(Calendar.YEAR);
            // 获取月份
            int month = instance.get(Calendar.MONTH) + 1;
            // 获取日
            int day = instance.get(Calendar.DATE);
            System.out.println(currentYear+ "------" + month+ "-----" + day);
            if (year.get() == currentYear) {
                articleMap.put("id", articles.get(i).getId());
                articleMap.put("title", articles.get(i).getTitle());
                // 将文章信息放入文章集集合中
                articleList.add(articleMap);
                //判断articles集合长度
                if (articles.size() == i) {
                    // 将文章集合信息放入年份信息中
                    yearMap.put("articles", articleList);
                    timeList.add(yearMap);
                }
            } else {
                if (year.get() == 0) { // 第一次遍历时，文章信息
                    year.set(currentYear);
                    yearMap.put("year", year.get());
                    articleMap.put("id", articles.get(i).getId());
                    articleMap.put("title", articles.get(i).getTitle());
                    // 将文章信息放入文章集集合中
                    articleList.add(articleMap);
                    //判断articles集合长度
                    if (articles.size() == 1) {
                        // 将文章集合信息放入年份信息中
                        yearMap.put("articles", articleList);
                        timeList.add(yearMap);
                    }
                } else { // 不同年份文章
                    // 先添加上次的文章信息
                    yearMap.put("articles", articleList);
                    timeList.add(yearMap);
                    // 在获取本次文章信息
                    year.set(currentYear);
                    yearMap.put("year", year.get());
                    articleMap.put("id", articles.get(i).getId());
                    articleMap.put("title", articles.get(i).getTitle());
                    //清空articleList
                    articleList.clear();
                    articleList.add(articleMap);
                    //判断articles集合长度
                    if (articles.size() == i) {
                        yearMap.put("articles", articleList);
                        timeList.add(yearMap);
                    }
                }
            }
        }



    }

}
