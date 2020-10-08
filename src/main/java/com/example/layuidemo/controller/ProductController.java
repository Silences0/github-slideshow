package com.example.layuidemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.layuidemo.dao.ProductDao;
import com.example.layuidemo.entity.Product;
import com.example.layuidemo.service.ProductService;
import com.github.pagehelper.StringUtil;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDao productDao;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/shop/product/list")
    public JSONObject productList(int page, @RequestParam(required = false, defaultValue = "10") int limit, String keyword) {
        //采用搜索引擎存储商品数据的信息
        //采用消息队列实现数据同步
        //1.创建QueryBuilder(即设置查询条件)这儿创建的是组合查询(也叫多条件查询),后面会介绍更多的查询方法
        /*组合查询BoolQueryBuilder
         * must(QueryBuilders)   :AND
         * mustNot(QueryBuilders):NOT
         * should:               :OR
         */
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        //builder下有must、should以及mustNot 相当于sql中的and、or以及not

        //设置模糊搜索,博客的简诉中有学习两个字
        if (StringUtil.isNotEmpty(keyword)) {
            builder.must(QueryBuilders.fuzzyQuery("productName", keyword).fuzziness(Fuzziness.ONE));
        }
        //设置要查询博客的标题中含有关键字
        //builder.must(new QueryStringQueryBuilder("man").field("springdemo"));

        //按照博客的评论数的排序是依次降低
        FieldSortBuilder sort = SortBuilders.fieldSort("id").order(SortOrder.DESC);

        //设置分页(从第一页开始，一页显示10条)
        //注意开始是从0开始，有点类似sql中的方法limit 的查询
        if (page>=1){
            page=page-1;
        }
        PageRequest pageRequest = new PageRequest(page, limit);

        //2.构建查询
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //将搜索条件设置到构建中
        nativeSearchQueryBuilder.withQuery(builder);
        //将分页设置到构建中
        nativeSearchQueryBuilder.withPageable(pageRequest);
        //将排序设置到构建中
        nativeSearchQueryBuilder.withSort(sort);
        //生产NativeSearchQuery
        NativeSearchQuery query = nativeSearchQueryBuilder.build();

        //3.执行方法1
        Page<Product> blogPage = productDao.search(query);
        //执行方法2：注意，这儿执行的时候还有个方法那就是使用elasticsearchTemplate
        //执行方法2的时候需要加上注解
        //@Autowired
        //private ElasticsearchTemplate elasticsearchTemplate;
        List<Product> blogList = elasticsearchTemplate.queryForList(query, Product.class);

        //4.获取总条数(用于前端分页)
        int total = (int) blogPage.getTotalElements();

        //5.获取查询到的数据内容（返回给前端）
        List<Product> content = blogPage.getContent();
        /**
         * 全量数据导入
         * 在第一次将存储在数据库里面的数据导入到ES需要执行全量导入，
         * 当后续有数据更新时通过消息队列通知ES更新数据。
         * 消息队列实现增量同步的方式，是在主服务对数据库进行创建、
         * 删除、修改一条记录时，发布一条主题消息给消息队列，
         * 同时同步服务需要订阅相关主题，这样消息队列就可以将更新的记录转发给同步服务，
         * 同步服务再根据消息的内容在ES里面进行更新记录。
         * 消息队列实现增量同步除了可以解耦主服务和同步服务，
         * 还有一个好处就是保证同步的容错性，比如当数据库添加一条记录时，
         * 如果直接采用HTTP的方式（可能是一个post请求）与同步服务取得联系时出现连接失败、
         * post请求失败的时候，如果不采取任何措施这条记录就会无法得到同步。
         * 而消息队列的失败重发的机制可以很好的解决这个问题，同时消息队列，
         * FIFO（先进先出）的机制也保证了消息转发的顺序。
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", content);
        jsonObject.put("code", 0);
        jsonObject.put("count", total);
        jsonObject.put("msg", "");
        return jsonObject;
    }
}
