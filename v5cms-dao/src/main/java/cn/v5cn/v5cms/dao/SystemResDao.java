package cn.v5cn.v5cms.dao;

import cn.v5cn.v5cms.entity.SystemRes;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZXF-PC1 on 2015/6/23.
 */
@Repository("systemResDao")
public interface SystemResDao {
//    @Query("select sr from SystemRes sr where 1=1 order by CONCAT(sr.pids,sr.id)")
    List<SystemRes> findOrderByPidsAndId();

    List<SystemRes> findByPid(Long pid);
}
