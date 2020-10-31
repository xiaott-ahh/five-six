package com.fivesix.fivesixserver.filter;

import com.fivesix.fivesixserver.service.PermissionService;
import com.fivesix.fivesixserver.util.SpringContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class URLPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    PermissionService permissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        //Option请求放行
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        if (permissionService == null) {
            permissionService = SpringContextUtil.getContext().getBean(PermissionService.class);
        }

        String requestAPI = getPathWithinApplication(httpServletRequest);
        System.out.println("请求接口:" + requestAPI);

        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            System.out.println("还未登陆");
            return false;
        }

        if (!permissionService.requireFilter(requestAPI)) {
            System.out.println("接口:" + requestAPI + ">无需权限");
            return true;
        }else {
            System.out.println("验证是否有对应的权限");
            //获取当前用户的所有权限
            String username = subject.getPrincipal().toString();
            Set<String> permittedAPIs = permissionService.listPermissionUrlsOfUser(username);
            boolean hasPermission = false;
            for (String permittedUrl : permittedAPIs) {
                if (requestAPI.startsWith(permittedUrl)){
                    hasPermission = true;
                    break;
                }
            }
            if (hasPermission) {
                System.out.println("该用户拥有访问接口:" + requestAPI + "的权限");
                return true;
            } else {
                System.out.println("该用户没有访问接口" + requestAPI + "的权限");
                return false;
            }
        }

    }
}
