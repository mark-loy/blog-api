package com.markloy.markblog.service.impl;

import com.markloy.markblog.mapper.ArticleMapper;
import com.markloy.markblog.pojo.Article;
import com.markloy.markblog.pojo.ArticleExample;
import com.markloy.markblog.service.TimeLineService;
import com.markloy.markblog.util.DeepCopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TimeLineServiceImpl implements TimeLineService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据时间倒序获取文章信息，处理返回结果
     * @return
     */
    @Override
    public Map<String, Object> getTimeLineData() throws IOException, ClassNotFoundException {
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
            // 日期处理
            Date date = new Date(Long.parseLong(articles.get(i).getGmtCreate() + ""));
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            // 获取年份
            int currentYear = instance.get(Calendar.YEAR);
            // 获取月份
            int month = instance.get(Calendar.MONTH) + 1;
            // 获取日
            int day = instance.get(Calendar.DATE);
            if (year.get() == currentYear) {
                articleMap.put("id", articles.get(i).getId());
                articleMap.put("title", articles.get(i).getTitle());
                articleMap.put("date", month + "-" + day);
                // 将文章信息放入文章集集合中
                articleList.add(articleMap);
                //判断articles集合长度
                if (articles.size() - 1 == i) {
                    // 将文章集合信息放入年份信息中
                    yearMap.put("year", year.get());
                    yearMap.put("articles", articleList);
                    timeList.add(yearMap);
                }
            } else {
                if (year.get() == 0) { // 第一次遍历时的文章信息
                    year.set(currentYear);
                    yearMap.put("year", year.get());
                    articleMap.put("id", articles.get(i).getId());
                    articleMap.put("title", articles.get(i).getTitle());
                    articleMap.put("date", month + "-" + day);
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
                    // 深拷贝articleList集合，相当于把每年的文章数据隔离
                    List<Map<String, Object>> mapList = DeepCopyUtil.depCopy(articleList);
                    yearMap.put("year", year.get());
                    yearMap.put("articles", mapList);
                    timeList.add(yearMap);
                    // 在获取本次文章信息
                    year.set(currentYear);
                    articleMap.put("id", articles.get(i).getId());
                    articleMap.put("title", articles.get(i).getTitle());
                    articleMap.put("date", month + "-" + day);
                    //清空articleList
                    articleList.clear();
                    articleList.add(articleMap);
                    //判断articles集合长度
                    if (articles.size() - 1 == i) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("year", year.get());
                        map.put("articles", articleList);
                        timeList.add(map);
                    }
                }
            }
        }
        // 结果集封装
        Map<String, Object> map = new HashMap<>();
        map.put("total", articles.size());
        map.put("timeLine", timeList);
        return map;
    }
}
