package cn.v5cn.v5cms.action;

import cn.v5cn.v5cms.biz.ColumnTypeBiz;
import cn.v5cn.v5cms.entity.ColumnType;
import cn.v5cn.v5cms.entity.Site;
import cn.v5cn.v5cms.exception.V5CMSNullValueException;
import cn.v5cn.v5cms.util.HttpUtils;
import cn.v5cn.v5cms.util.SystemConstant;
import cn.v5cn.v5cms.util.SystemUtils;
import cn.v5cn.v5cms.util.TwoTuple;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

import static cn.v5cn.v5cms.util.MessageSourceHelper.getMessage;

/**
 * Created by ZYW on 2014/8/12.
 */
@Controller
@RequestMapping("/manager/coltype")
public class ColumnTypeAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ColumnTypeAction.class);

    @Autowired
    private ColumnTypeBiz columnTypeBiz;

    @RequestMapping(value = "/list/{p}",method = RequestMethod.GET)
    public String colTypeList(ColumnType columnType,@PathVariable Integer p,HttpSession session,HttpServletRequest request,ModelMap modelMap){
        if(StringUtils.isNotBlank(columnType.getColTypeName())){
            session.setAttribute("columnTypeSearch",columnType);
            modelMap.addAttribute("colTypeNameSearch",columnType.getColTypeName());
        }else {
            session.setAttribute("columnTypeSearch",null);
        }
        Object searchObj = session.getAttribute("columnTypeSearch");

        ColumnType colType = searchObj == null ? (new ColumnType()) : ((ColumnType) searchObj);

        Site site = (Site)(SystemUtils.getSessionSite(session));
        colType.setSiteId(site.getSiteId());

        Page<ColumnType> pageColumnTypes = columnTypeBiz.findColumnTypeByColTypeNamePageable(colType, p);
        modelMap.addAttribute("cts",pageColumnTypes.getContent());
        modelMap.addAttribute("pagination", SystemUtils.pagination(pageColumnTypes, HttpUtils.getContextPath(request) + "/manager/coltype/list"));
        return "coltype_list";
    }

    @RequestMapping(value = "/edit/{colTypeId}",method = RequestMethod.GET)
    public String columnTypeEdit(@PathVariable Long colTypeId,ModelMap modelMap,HttpSession session){

        Site site = (Site)(SystemUtils.getSessionSite(session));
        String templateBasePath = session.getServletContext().getRealPath(SystemConstant.SITE_TEMPLATE_PATH);
        String themeName = site.getThemeName();
        if(StringUtils.isBlank(themeName)){
            LOGGER.error("{}站点没有保存主题信息",site.getSiteName());
            throw new V5CMSNullValueException("站点没有保存主题信息");
        }
        File templateFile = new File(templateBasePath+File.separator+themeName);
        List<TwoTuple<String,String>> mapList = columnTypeBiz.templatePathAndName(templateFile);


        List<TwoTuple<String,String>> result = Lists.newArrayList();
        for(TwoTuple<String,String> nameAndUri : mapList){
            String uri = StringUtils.remove(nameAndUri.b, templateBasePath + File.separator + themeName);
            result.add(new TwoTuple<String, String>(nameAndUri.a,uri));
        }

        modelMap.addAttribute("templates",result);

        if(colTypeId == 0){
            modelMap.addAttribute(new ColumnType());
        }else{
            ColumnType columnType = columnTypeBiz.findOne(colTypeId);
            if(columnType == null){
                LOGGER.error("ID为{}的栏目类型数据没有查到！",colTypeId);
                throw new V5CMSNullValueException("ID为"+colTypeId+"的栏目类型数据没有查到！");
            }
            modelMap.addAttribute(columnType);
        }

        return "coltype_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ImmutableMap<String,String> columnTypeEdit(ColumnType columnType,HttpSession session){
        //设置站点ID
        Site site = (Site)(SystemUtils.getSessionSite(session));
        columnType.setSiteId(site.getSiteId());
        if(columnType.getColTypeId() == null){

            ColumnType saveColType = columnTypeBiz.save(columnType);
            if(saveColType.getColTypeId() != null){
                LOGGER.info("栏目类型添加成功，{}",saveColType);
                return ImmutableMap.of("status","1","message",getMessage("column.type.addsuccess.message"));
            }
            LOGGER.info("栏目类型添加失败，{}",saveColType);
            return ImmutableMap.of("status","0","message",getMessage("column.type.addfailed.message"));
        }
//        try {
//            columnTypeBiz.update(columnType);
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//            LOGGER.error("修改栏目类型失败，{},失败堆栈错误：{}",columnType,e.getMessage());
//            return ImmutableMap.of("status","0","message",getMessage("column.type.updatefailed.message"));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            LOGGER.error("修改栏目类型失败，{},失败堆栈错误：{}",columnType,e.getMessage());
//            return ImmutableMap.of("status","0","message",getMessage("column.type.updatefailed.message"));
//        }
//        LOGGER.info("修改栏目类型成功，{}",columnType);
//        return ImmutableMap.of("status","1","message",getMessage("column.type.updatesuccess.message"));
        try {
            columnTypeBiz.save(columnType);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改栏目类型失败，{},失败堆栈错误：{}",columnType,e.getMessage());
            return ImmutableMap.of("status","0","message",getMessage("column.type.updatefailed.message"));
        }
        return ImmutableMap.of("status","1","message",getMessage("column.type.updatesuccess.message"));
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ImmutableMap<String,String> colTypeDelete(Long[] colTypeIds){
        try {
            columnTypeBiz.deleteColType(colTypeIds);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除栏目类型信息失败，失败原因：{}",e.getMessage());
            return ImmutableMap.of("status","1","message",getMessage("column.type.deletefailed.message"));
        }
        LOGGER.info("删除栏目类型信息，ID为{}",StringUtils.join(colTypeIds,","));
        return ImmutableMap.of("status","1","message",getMessage("column.type.deletesuccess.message"));
    }
}
