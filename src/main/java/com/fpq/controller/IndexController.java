/**
* @Title: IndexController.java
* @Package com.fpq.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author slx
* @date 2019年5月3日
* @version V1.0
*/
package com.fpq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
* @classDesc: 功能描述：
* @author 付品欣
* @createTime 2019年5月3日 下午5:37:13
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@RestController
@Slf4j
public class IndexController {

	/**
	 * springCloud2-zuul
	 */
	private String e;
	
	/**
	 * http://127.0.0.1:8100/index
	* @Title: index
	* @Description: TODO(测试应用是否其他成功)
	* @param @return    参数
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("/index")
	public String index() {
		String result = "zuul网关应用springCloud2-zuul已启动，正常对外提供服务。";
		log.info(result);
		return result;
	}
	
	
//	public String testApollo() {
//		
//	}
}
