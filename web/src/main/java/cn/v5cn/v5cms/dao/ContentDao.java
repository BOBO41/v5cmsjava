package cn.v5cn.v5cms.dao;

import cn.v5cn.v5cms.entity.Content;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ZYW on 2014/11/19.
 */
@Repository("contentDao")
public interface ContentDao extends PagingAndSortingRepository<Content,Long> {
}
