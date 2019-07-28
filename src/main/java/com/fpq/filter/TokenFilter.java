/**
* @Title: TokenFilter.java
* @Package com.fpq.filter
* @Description: TODO(zuul网关中开发过滤器，过滤掉为登入的请求)
* @author slx
* @date 2019年5月4日
* @version V1.0
*/
package com.fpq.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

/**
* @classDesc: 功能描述：token过滤器过滤为登入的请求
* @author 付品欣
* @createTime 2019年5月4日 下午3:02:50
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@Component
@Slf4j
public class TokenFilter extends ZuulFilter{

	/**
	 * 端口号
	 */
	@Value("${server.port:80}")
	private String serverPort;
	
	/**
	 * 判断过滤器是否生效,true:生效，false:未生效
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器执行的业务逻辑代码：拦截所有的请求，判断是否有token(是否登入)
	 */
	@Override
	public Object run() throws ZuulException {
		//过滤器中获取上下文
		RequestContext currentContext = RequestContext.getCurrentContext();
		//获取request请求对象
		HttpServletRequest request = currentContext.getRequest();
		//获取token,企业级项目需要从请求header中获取
		String userToken = request.getParameter("userToken");
		String password = request.getParameter("password");
		//判断token是否为空，若为空这返回错误信息
		if (StringUtils.isEmpty(userToken) || StringUtils.isEmpty(password)) {
			//response设置为false,终止请求，不会调用对于的接口，zuul网关直接返回
			currentContext.setSendZuulResponse(false);
			//设置response响应码为401
			currentContext.setResponseStatusCode(401);
			String result = "userToken is null，禁止访问，请先登入！";
			//设置response响应体
			currentContext.setResponseBody(result);
			log.info(result);
			return null;
		}else {
			
			if("admin".equalsIgnoreCase(password)) {
				
				// 否则正常执行业务逻辑，调用其他服务接口.....
				log.info("当前的zuul网关的端口号："+this.serverPort);
				log.info(request.getParameter("userToken")+"已登入，请继续访问"+request.getRequestURI()+"?"+request.getParameterMap().toString());
				return null;
			}else {
				//response设置为false,终止请求，不会调用对于的接口，zuul网关直接返回
				currentContext.setSendZuulResponse(false);
				//设置response响应码为401
				currentContext.setResponseStatusCode(402);
				String result = "输入的用户名或密码不对，请重新输入！";
				//设置response响应体
				currentContext.setResponseBody(result);
				log.info(result);
				return null;
			}
		}
		
	}

	/**
	 * 过滤器的类型，pre:请求之前执行；post:请求后执行
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 过滤器的执行顺序。当请求在一个阶段的时候存在多个多个过滤器时，需要根据该方法的返回值依次执行
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

}
