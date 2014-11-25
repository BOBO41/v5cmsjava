package cn.v5cn.v5cms.action;

import cn.v5cn.v5cms.biz.ColumnBiz;
import cn.v5cn.v5cms.biz.ColumnTypeBiz;
import cn.v5cn.v5cms.entity.Column;
import cn.v5cn.v5cms.entity.ColumnType;
import cn.v5cn.v5cms.entity.Site;
import cn.v5cn.v5cms.entity.wrapper.ZTreeNode;
import cn.v5cn.v5cms.util.SystemUtils;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

import static cn.v5cn.v5cms.util.MessageSourceHelper.getMessage;

/**
 * Created by ZYW on 2014/8/7.
 */
@Controller
@RequestMapping("/manager/column")
public class ColumnAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColumnAction.class);

    @Autowired
    private ColumnBiz columnBiz;

    @Autowired
    private ColumnTypeBiz columnTypeBiz;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String columnList(ModelMap modelMap,HttpSession session){
        Site site = (Site)(SystemUtils.getSessionSite(session));
        List<Column> columns = columnBiz.findOrderByParentIdsAndColsId(site.getSiteId());
        System.out.println(columns);
        modelMap.addAttribute("columns",columns);
        return "column_list";
    }

    @RequestMapping(value = "/edit/{columnId}",method = RequestMethod.GET)
    public String columnEdit(@PathVariable Long columnId,ModelMap modelMap){
        Column column = new Column();
        if(columnId == 0){
            column.setParentIds("0/");
            column.setParentId(0L);
            modelMap.addAttribute("column",column);
            modelMap.addAttribute("parentName",getMessage("column.first"));
        }else{
            Column dbColumn = columnBiz.findOne(columnId);
            column.setParentIds(dbColumn.getParentIds()+dbColumn.getColsId()+"/");
            column.setParentId(dbColumn.getColsId());
            modelMap.addAttribute("parentName",dbColumn.getColumnName());
            modelMap.addAttribute("column",column);
        }
        List<ColumnType> colTypes = columnTypeBiz.findAll();
        modelMap.addAttribute("colTypes",colTypes);
        return "column_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ImmutableMap<String,String> columnEdits(Column column,HttpSession session){
        if(column.getColsId() == null || column.getColsId() == 0L){
            //设置站点ID
            Site site = (Site)(SystemUtils.getSessionSite(session));
            column.setSiteId(site.getSiteId());

            column = columnBiz.save(column);
            if(column.getColsId() != null){
                LOGGER.info("栏目添加成功,{}",column);
                return ImmutableMap.of("status","1","message",getMessage("column.addsuccess.message"));
            }
            LOGGER.error("栏目添加失败,{}",column);
            return ImmutableMap.of("status","0","message",getMessage("column.addfailed.message"));
        }
        //修改
        try {
            columnBiz.save(column);
        } catch (Exception e) {
            LOGGER.error("栏目修改失败,{}",e);
            e.printStackTrace();
            return ImmutableMap.of("status","0","message",getMessage("column.updatefailed.message"));
        }
        LOGGER.info("栏目添加成功,{}",column);
        return ImmutableMap.of("status","1","message",getMessage("column.updatesuccess.message"));
    }

    @RequestMapping(value = "/{colId}/update")
    public String columnUpdate(@PathVariable Long colId,ModelMap modelMap){
        Column column = columnBiz.findOne(colId);
        List<ColumnType> colTypes = columnTypeBiz.findAll();
        modelMap.addAttribute("colTypes",colTypes);
        modelMap.addAttribute("column",column);
        if(column.getParentId() == 0){
            modelMap.addAttribute("parentName",getMessage("column.first"));
        }else{
            Column parentColumn = columnBiz.findOne(column.getParentId());
            modelMap.addAttribute("parentName",parentColumn.getColumnName());
        }
        return "column_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ImmutableMap<String,String> columnDelete(Long columnId){
        try {
            columnBiz.delete(columnId);
        } catch (Exception e) {
            LOGGER.info("栏目删除失败,ID:{}。失败信息：{}",columnId,e.getMessage());
            return ImmutableMap.of("status","0","message",getMessage("column.deletefailed.message"));
        }
        LOGGER.info("栏目删除成功,ID:{}",columnId);
        return ImmutableMap.of("status","1","message",getMessage("column.deletesuccess.message"));
    }

    @ResponseBody
    @RequestMapping(value = "/tree/json",method = RequestMethod.POST)
    public List<ZTreeNode> columnTree(){
        List<ZTreeNode> treeNodes = columnBiz.buildTreeNode(0L);
        LOGGER.debug("treeNodes: " + treeNodes);
        return treeNodes;
    }
}
