package com.shijt.OAuth2.WebSecurity;

import com.shijt.OAuth2.dao.ResourceDao;
import com.shijt.OAuth2.vo.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

@Service
public class FilterInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceDao resourceDao;

    private HashMap<String,Collection<ConfigAttribute>> cfgAttrMap=null;

    /**
     * 返回请求url需要的权限
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(cfgAttrMap==null)loadResources();

        HttpServletRequest request=((FilterInvocation)object).getHttpRequest();
        Iterator<String> iterator=cfgAttrMap.keySet().iterator();
        String url;
        for(;iterator.hasNext();){
            url=iterator.next();
            AntPathRequestMatcher pathMatcher=new AntPathRequestMatcher(url);
            if(pathMatcher.matches(request)){
                return cfgAttrMap.get(url);
            }
        }
        return null;
    }

    /**
     * 加载所有resource
     */
    private void loadResources() {
        cfgAttrMap=new HashMap<>();
        Iterable<Resource> resources= resourceDao.findAll();
        resources.forEach(resource->{
            Collection<ConfigAttribute> cfgAttrs=new ArrayList<>();
            ConfigAttribute cfgAttr=new SecurityConfig(""+resource.getId());
            cfgAttrs.add(cfgAttr);
            cfgAttrMap.put(resource.getUrl(),cfgAttrs);

        });
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
