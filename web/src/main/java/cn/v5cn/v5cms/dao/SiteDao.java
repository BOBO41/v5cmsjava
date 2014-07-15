package cn.v5cn.v5cms.dao;

import cn.v5cn.v5cms.entity.Site;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ZYW on 2014/6/30.
 */
@Repository("siteDao")
public interface SiteDao extends CrudRepository<Site,Long> {
//    /**
//     * 根据站点ID查询站点。
//     * 当siteId等于-1时查询全部的站点信息
//     * */
//    ImmutableList<Site> findSite(int siteId);
//    /**
//     * 根据运行状态值查询站点
//     * */
//    ImmutableList<Site> findRunableSite(int isclosesite);
//    /**
//     * 添加站点信息
//     * */
//    Long addSite(Site site);
//    /**
//     * 修改站点信息
//     * */
//    int updateSite(Site site);
//    /**
//     * 删除站点信息
//     * */
//    int deleteSite(int siteId);
}
