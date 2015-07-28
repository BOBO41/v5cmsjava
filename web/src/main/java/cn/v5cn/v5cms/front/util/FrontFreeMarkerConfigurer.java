package cn.v5cn.v5cms.front.util;

import cn.v5cn.v5cms.front.freemarker.V5CMSDirectives;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * Created by ZXF-PC1 on 2015/7/28.
 */
public class FrontFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Autowired
    private V5CMSDirectives v5CMSDirectives;

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        getConfiguration().setSharedVariable("v5cms",v5CMSDirectives);
    }
}
