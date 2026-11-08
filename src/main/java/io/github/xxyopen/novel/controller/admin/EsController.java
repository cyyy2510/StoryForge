package io.github.xxyopen.novel.controller.admin;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import io.github.xxyopen.novel.core.common.resp.RestResp;
import io.github.xxyopen.novel.dao.entity.BookInfo;
import io.github.xxyopen.novel.dao.mapper.BookInfoMapper;
import io.github.xxyopen.novel.dto.es.EsBookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/es")
@RequiredArgsConstructor
public class EsController {
    private final BookInfoMapper bookInfoMapper;
    private final ElasticsearchClient esClient;

    /**
     * 全量导入小说数据到ES
     */
    @PostMapping("/init/book")
    public RestResp<String> initBookIndex() {
        // 1. 从MySQL查出所有小说（数据量大的话要分页查，这里演示全量查）
        List<BookInfo> bookList = bookInfoMapper.selectList(null);
        log.info("开始全量导入ES，共{}本小说", bookList.size());

        int successCount = 0;
        for (BookInfo book : bookList) {
            try {
                // 2. 复用项目已有的EsBookDto.build()转换
                EsBookDto esBook = EsBookDto.build(book);
                // 3. 写入ES，id=小说id，存在则覆盖
                IndexResponse response = esClient.index(i -> i
                        .index("book")  // 换成你的常量 EsConsts.BookIndex.INDEX_NAME
                        .id(book.getId().toString())
                        .document(esBook)
                );
                successCount++;
            } catch (Exception e) {
                log.error("导入小说失败，bookId={}", book.getId(), e);
            }
        }
        log.info("全量导入ES完成，成功{}条，共{}条", successCount, bookList.size());
        return RestResp.ok("导入完成，成功" + successCount + "条，共" + bookList.size() + "条");
    }
}
