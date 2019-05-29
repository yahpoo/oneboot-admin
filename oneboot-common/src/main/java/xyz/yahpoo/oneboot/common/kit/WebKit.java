package xyz.yahpoo.oneboot.common.kit;

import javax.servlet.http.HttpServletRequest;

public class WebKit {

    /**
     * TODO 是否是ajax请求
     * @param request
     * @return
     */
    public boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (null != header && "XMLHttpRequest".equals(header)){
            return true;
        } else{
            return false;
        }
    }

}
