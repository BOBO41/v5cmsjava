package cn.v5cn.v5cms.action;

import cn.v5cn.v5cms.biz.AdvBiz;
import cn.v5cn.v5cms.biz.AdvPosBiz;
import cn.v5cn.v5cms.entity.Adv;
import cn.v5cn.v5cms.entity.AdvPos;
import cn.v5cn.v5cms.entity.wrapper.AdvWrapper;
import cn.v5cn.v5cms.util.HttpUtils;
import cn.v5cn.v5cms.util.SystemConstant;
import cn.v5cn.v5cms.util.SystemUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cn.v5cn.v5cms.util.MessageSourceHelper.getMessage;

/**
 * Created by ZYW on 2014/7/24.
 */
@Controller
@RequestMapping("/manager")
public class AdvAction {

    private final static Logger LOGGER = LoggerFactory.getLogger(AdvAction.class);

    @Autowired
    private AdvPosBiz advPosBiz;
    @Autowired
    private AdvBiz advBiz;

    @RequestMapping(value = "/advlist",method = {RequestMethod.GET,RequestMethod.POST})
    public String advList(@ModelAttribute Adv adv,
                          @RequestParam(value = "p",defaultValue = "0")int currPage,
                          ModelMap modelMap,HttpServletRequest request){

        HttpSession session = request.getSession();
        if("GET".equalsIgnoreCase(request.getMethod())){
            adv = (Adv)session.getAttribute("search_adv");
        }
        if("POST".equalsIgnoreCase(request.getMethod())){
            session.setAttribute("search_adv",null);
            if(StringUtils.isNotBlank(adv.getAdvName()) || adv.getAdvPos().getAdvPosId() != null){
                session.setAttribute("search_adv",adv);
            }
        }
        Page<Adv> pageListAdv =  advBiz.findAdvByAdvNamePageable((adv == null ? (new Adv()):adv),currPage);
        modelMap.addAttribute("advs",pageListAdv);
        modelMap.addAttribute("pagination",SystemUtils.pagination(pageListAdv,HttpUtils.getBasePath(request)+"/manager/advlist"));
        ImmutableList<AdvPos> advposes = advPosBiz.finadAll();
        modelMap.addAttribute("aps",advposes);

        return "backstage/adv_list";
    }

    @RequestMapping(value = "/advaup",method = RequestMethod.GET)
    public String advPosaup(ModelMap model){
        ImmutableList<AdvPos> advposes = advPosBiz.finadAll();
        model.addAttribute("aps",advposes);
        model.addAttribute(new Adv());
        model.addAttribute("advTypes", Maps.newHashMap());
        return "backstage/adv_au";
    }

    @RequestMapping(value = "/advaup/{advId}",method = RequestMethod.GET)
    public String advPosaup(@PathVariable Long advId,ModelMap model){
        ImmutableList<AdvPos> advposes = advPosBiz.finadAll();
        model.addAttribute("aps",advposes);
        Adv adv = advBiz.findOne(advId);
        ObjectMapper jsonObj = new ObjectMapper();
        Map<String,String> advTypeMap = null;
        try {
            advTypeMap = jsonObj.readValue(adv.getAdvTypeInfo(),Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("JSON转换Map对象异常，异常消息{}",e.getMessage());
        }
        model.addAttribute(adv);
        model.addAttribute("advTypes",advTypeMap);
        model.addAttribute("page_title",getMessage("adv.updatepage.title"));
        return "backstage/adv_au";
    }

    @ResponseBody
    @RequestMapping(value = "/advau",method = RequestMethod.POST)
    public ImmutableMap<String,Object> advAU(@ModelAttribute("adv") AdvWrapper advWrapper,HttpServletRequest request){
        List<String> deleteFilePaths = (List<String>)request.getSession().getAttribute("adv_delete_file_real_path");
        if(deleteFilePaths != null && deleteFilePaths.size() > 0){
            for(String deleteFilePath : deleteFilePaths){
                String fileExt = FilenameUtils.getExtension(deleteFilePath);
                boolean result = FileUtils.deleteQuietly(new File(deleteFilePath));
                if(!result){
                    //String failedMessage = "swf".equalsIgnoreCase(fileExt)?getMessage("adv.flashdeletefailed.message"):getMessage("adv.imagedeletefailed.message");
                    LOGGER.warn("广告图片或者Flash删除失败，名称{}",deleteFilePath);
                    //return ImmutableMap.<String,Object>of("status","0","message",failedMessage);
                }
            }
            request.getSession().setAttribute("adv_delete_file_real_path",null);
        }
        if(advWrapper.getAdv().getAdvId() != null){
            try {
                advBiz.update(advWrapper);
                return ImmutableMap.<String,Object>of("status","1","message",getMessage("adv.updatesuccess.message"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                LOGGER.error("修改广告失败，{}",e.getMessage());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                LOGGER.error("修改广告失败，{}",e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("修改广告失败，{}",e.getMessage());
            }
            return ImmutableMap.<String,Object>of("status","0","message",getMessage("adv.updatefailed.message"));
        }
        LOGGER.info("添加广告信息，{}",advWrapper);
        try {
            Adv adv= advBiz.save(advWrapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.error("添加广告时转换JSON出错！{}",e.getMessage());
            ImmutableMap.<String,Object>of("status","0","message",getMessage("adv.addfailed.message"));
        }
        return ImmutableMap.<String,Object>of("status","1","message",getMessage("adv.addsuccess.message"));
    }

    @ResponseBody
    @RequestMapping(value = "/advupload",method = RequestMethod.POST)
    public ImmutableMap<String,Object> advUploader(MultipartFile file,HttpServletRequest request){
        if(file.isEmpty()){
            return ImmutableMap.<String,Object>of("status","0","message",getMessage("global.uploadempty.message"));
        }
        String realPath = HttpUtils.getRealPath(request, SystemConstant.ADV_RES_PATH);

        SystemUtils.isNotExistCreate(realPath);
        String timeFileName = SystemUtils.timeFileName(file.getOriginalFilename());
        try {
            file.transferTo(new File(realPath+"/"+timeFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return ImmutableMap.<String,Object>of("status","0","message",getMessage("adv.uploaderror.message"));
        }
        return ImmutableMap.<String,Object>of("status","1","message",getMessage("adv.uploadsuccess.message"),
                "filePath","/r/advfiles/"+timeFileName,"contentPath", HttpUtils.getBasePath(request));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteadvif",method = RequestMethod.POST)
    public ImmutableMap<String,Object> deleteAdvImage(String if_path,HttpServletRequest request){
        String fileExt = FilenameUtils.getExtension(if_path);
        String fileName = FilenameUtils.getName(if_path);
        String realPath = HttpUtils.getRealPath(request, SystemConstant.ADV_RES_PATH);
        List<String> deletePaths = (List<String>)request.getSession().getAttribute("adv_delete_file_real_path");
        if(deletePaths == null){
            deletePaths = Lists.newArrayList();
        }
        deletePaths.add(realPath+"/"+fileName);

        request.getSession().setAttribute("adv_delete_file_real_path",deletePaths);
        String failedMessage = "swf".equalsIgnoreCase(fileExt)?getMessage("adv.flashdelete.before.message"):getMessage("adv.imagedelete.before.message");
        return ImmutableMap.<String,Object>of("status","1","message",failedMessage);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteadvs",method = RequestMethod.POST)
    public ImmutableMap<String,Object> deleteAdvs(Long[] advIds,HttpServletRequest request){
        LOGGER.info("删除广告信息，ID为{}",advIds);
        try {
            advBiz.deleteAdvs(advIds,request);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除广告信息失败，ID为{}",advIds);
            return ImmutableMap.<String,Object>of("status","0","message",getMessage("adv.deletefailed.message"));
        }
        return ImmutableMap.<String,Object>of("status","1","message",getMessage("adv.deletesuccess.message"));
    }
}
