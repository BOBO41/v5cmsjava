package cn.v5cn.v5cms.biz;

import cn.v5cn.v5cms.entity.Column;
import cn.v5cn.v5cms.entity.wrapper.ZTreeNode;

import java.util.List;

/**
 * Created by ZYW on 2014/10/31.
 */
public interface ColumnBiz {

    Column save(Column column);

    List<Column> findAll();
    List<Column> findOrderByParentIdsAndColsId(Long siteId);
    Column findOne(Long columnId);

    void delete(Long columnId);

    List<Column> findByParentId(Long parentId);

    List<ZTreeNode> buildTreeNode(Long parentId);
}
