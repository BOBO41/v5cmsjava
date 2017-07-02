package cn.v5cn.v5cms.service.impl;

import cn.v5cn.v5cms.dao.SystemResMapper;
import cn.v5cn.v5cms.service.SystemResService;
import cn.v5cn.v5cms.entity.SystemRes;
//import cn.v5cn.v5cms.entity.wrapper.ZTreeNode;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZXF-PC1 on 2015/6/23.
 */
@Service("systemResService")
public class SystemResServiceImpl implements SystemResService {

    @Autowired
    private SystemResMapper systemResMapper;

    @Override
    public List<SystemRes> findOrderByPidsAndId() {
        return systemResMapper.findOrderByPidsAndId();
    }

    @Override //ZTreeNode
    public List<Object> buildTreeNode(Long parentId) {
        /*List<SystemRes> rootNode = systemResDao.findByPid(parentId);
        if(rootNode == null || rootNode.size() < 1) return null;

        List<ZTreeNode> treeNodes = Lists.newArrayList();
        ZTreeNode treeNode = null;
        for(SystemRes res : rootNode){
            treeNode = new ZTreeNode();
            treeNode.setId(res.getId());
            treeNode.setName(res.getName());
            List<ZTreeNode> treeNodes1 = this.buildTreeNode(res.getId());
            if(treeNodes1 != null)
                treeNode.setChildren(treeNodes1);
            treeNodes.add(treeNode);
        }
        return treeNodes;*/
        return null;
    }

    @Override
    public List<SystemRes> findPermissionsByUserName(String userName) {
        return systemResMapper.findPermissionsByUserName(userName);
    }
}
