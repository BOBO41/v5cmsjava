package cn.v5cn.v5cms.controller;

import cn.v5cn.v5cms.util.PropertyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ZYW on 2014/8/13.
 */
@Controller
@RequestMapping("/manager/tpl")
public class TemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String tplList(HttpServletRequest request,ModelMap modelMap){
        String tplPath = PropertyUtils.getValue("tpl.path").or("/WEB-INF/ftls/front");
        String realTplPath = request.getSession().getServletContext().getRealPath(tplPath);
        File tplFile = new File(realTplPath);
        File[] tpls = tplFile.listFiles();

        List<Map<String,String>> result = Lists.newArrayList();

        Map<String,String> tplInfo = null;

        for(File tpl : tpls) {
            tplInfo = Maps.newHashMap();
            tplInfo.put("tplPath",tpl.getName());
            File[] tplJSON = tpl.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.equalsIgnoreCase("template.json");
                }
            });
            if(tplJSON == null || tplJSON.length == 0){
                LOGGER.error("模板中必须包含template.json模板文件。");
                throw new RuntimeException("模板中必须包含template.json模板文件。");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                Map map = objectMapper.readValue(tplJSON[0], Map.class);
                tplInfo.putAll(map);
            } catch (IOException e) {
                LOGGER.error("解析template.json出错，错误：{}", e.getMessage());
                throw new RuntimeException("解析template.json出错："+e.getMessage());
            }
            result.add(tplInfo);
        }
        modelMap.addAttribute("tplInfos",result);
        return "template/tpl_list";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String tplEdit(){
        return "template/tpl_edit";
    }

    @RequestMapping(value = "/thumb")
    public void thumbnail(HttpServletRequest request,HttpServletResponse response,
                          @Param("tplFileName") String tplFileName,@Param("thumbName") String thumbName){
        File thumb = thumbReader(request,tplFileName,thumbName);

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            Thumbnails.of(thumb).height(220).toOutputStream(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("读取缩略图报错：{}", e.getMessage());
            throw new RuntimeException("读取缩略图报错："+e.getMessage());
        }
    }
    @RequestMapping(value = "/thumb/original")
    public void thumbnailOriginal(HttpServletRequest request,HttpServletResponse response,
                          @Param("tplFileName") String tplFileName,@Param("thumbName") String thumbName){
        File thumb = thumbReader(request,tplFileName,thumbName);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            IOUtils.write(FileUtils.readFileToByteArray(thumb), outputStream);
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("读取缩略图报错：{}", e.getMessage());
            throw new RuntimeException("读取缩略图报错："+e.getMessage());
        }
    }
    private File thumbReader(HttpServletRequest request,String tplFileName,String thumbName){

        String tplPath = PropertyUtils.getValue("tpl.path").or("/WEB-INF/ftls/front");
        String realTplPath = request.getSession().getServletContext().getRealPath(tplPath);
        File thumb = new File(realTplPath+File.separator+tplFileName+File.separator+thumbName);

        return thumb;
    }
}
