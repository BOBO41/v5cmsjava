package cn.v5cn.v5cms.biz;

import cn.v5cn.v5cms.entity.SystemRole;
import org.springframework.data.domain.Page;

/**
 * Created by ZXF-PC1 on 2015/6/26.
 */
public interface SystemRoleBiz {
    Page<SystemRole> findRoleByRoleNamePageable(SystemRole role,Integer currPage);
}
