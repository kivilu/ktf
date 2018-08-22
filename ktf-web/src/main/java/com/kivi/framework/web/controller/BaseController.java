package com.kivi.framework.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.kivi.framework.component.ApplicationKit;
import com.kivi.framework.component.KtfProperties;
import com.kivi.framework.component.SpringContextHolder;
import com.kivi.framework.constant.enums.Order;
import com.kivi.framework.service.ITimeoutService;
import com.kivi.framework.util.FileUtil;
import com.kivi.framework.util.kit.StrKit;
import com.kivi.framework.vo.page.PageInfoBT;
import com.kivi.framework.vo.page.PageReqVO;
import com.kivi.framework.web.async.KtfAsyncResult;
import com.kivi.framework.web.async.KtfDeferredResult;
import com.kivi.framework.web.async.KtfTimeoutRunnable;
import com.kivi.framework.web.constant.tips.SuccessTip;
import com.kivi.framework.web.util.kit.HttpKit;
import com.kivi.framework.web.warpper.BaseControllerWarpper;

@DependsOn( value = { "springContextHolder", "ktfProperties" } )
public class BaseController {

    protected static String     SUCCESS     = "SUCCESS";
    protected static String     ERROR       = "ERROR";

    protected static String     REDIRECT    = "redirect:";
    protected static String     FORWARD     = "forward:";

    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return HttpKit.getRequest().getSession();
    }

    protected HttpSession getSession( Boolean flag ) {
        return HttpKit.getRequest().getSession(flag);
    }

    protected String getPara( String name ) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr( String name, Object value ) {
        HttpKit.getRequest().setAttribute(name, value);
    }

    protected Integer getSystemInvokCount() {
        return (Integer) this.getHttpServletRequest().getServletContext().getAttribute("systemCount");
    }

    public PageReqVO defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        sort = StrKit.toUnderlineCase(sort);
        PageReqVO pageReq = new PageReqVO(limit, offset, sort, order);
        if (StrKit.isEmpty(sort)) {
            pageReq.setOpenSort(false);
        }
        else {
            if (Order.ASC.getCode().equals(order)) {
                pageReq.setAsc(true);
            }
            else {
                pageReq.setAsc(false);
            }
        }
        return pageReq;
    }

    /**
     * 包装一个list，让list增加额外属性
     */
    protected Object warpObject( BaseControllerWarpper<?> warpper ) {
        return warpper.warp();
    }

    /**
     * 删除cookie
     */
    protected void deleteCookieByName( String cookieName ) {
        Cookie[] cookies = this.getHttpServletRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getHttpServletResponse().addCookie(temp);
            }
        }
    }

    /**
     * 返回前台文件流
     *
     */
    protected ResponseEntity<byte[]> renderFile( String fileName, String filePath ) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }

    /**
     * 返回前台文件流
     *
     */
    protected ResponseEntity<byte[]> renderFile( String fileName, byte[] fileBytes ) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }

    /**
     * 把service层的分页信息，封装为bootstrap table通用的分页封装
     */
    protected <T> PageInfoBT<T> packForBT( long total, List<T> list ) {
        return new PageInfoBT<T>(total, list);
    }

    protected String msgId() {
        return String.valueOf(ApplicationKit.me().nextId());
    }

    protected KtfDeferredResult newDeferredResult() {
        KtfProperties ktfProperties = SpringContextHolder.getBean(KtfProperties.class);

        ITimeoutService timeoutService = SpringContextHolder.getBean(ITimeoutService.class);

        KtfDeferredResult result = new KtfDeferredResult(msgId(), ktfProperties.getApi().getTimeout());
        result.onTimeout(new KtfTimeoutRunnable<String>(result, timeoutService));

        return result;
    }

    protected <T> KtfAsyncResult<T> newAsyncResultResult() {
        KtfProperties ktfProperties = SpringContextHolder.getBean(KtfProperties.class);

        ITimeoutService timeoutService = SpringContextHolder.getBean(ITimeoutService.class);

        KtfAsyncResult<T> result = new KtfAsyncResult<T>(msgId(), ktfProperties.getApi().getTimeout());
        result.onTimeout(new KtfTimeoutRunnable<T>(result, timeoutService));

        return result;
    }

}
