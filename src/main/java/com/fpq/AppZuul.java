/**
* @Title: AppZuul.java
* @Package com.fpq
* @Description: TODO(zuul网关的启动类文件)
* @author slx
* @date 2019年5月3日
* @version V1.0
* zuul网关整合了ribbon实现客户端的负载均衡
*/
package com.fpq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
/**
* @classDesc: 功能描述：zuul网关的启动类
* @author 付品欣
* @createTime 2019年5月3日 下午3:59:02
* @version: V1.0
* @copyright:深圳科翔教育科技有限公司
* @wechat:qhjx666888
*/
@SpringBootApplication
@EnableEurekaClient
@EnableApolloConfig //开启apollo客户端
@EnableZuulProxy //开启zuul网关代理
@EnableSwagger2Doc
public class AppZuul {

	/**
	* @Title: main
	* @Description: TODO(zuul网关启动类)
	* @param @param args    参数
	* @return void    返回类型
	* @throws
	*/
	public static void main(String[] args) {
		SpringApplication.run(AppZuul.class, args);
	}

	
	// 添加文档来源
		@Component
		@Primary
		class DocumentationConfig implements SwaggerResourcesProvider {
			@Override
			public List<SwaggerResource> get() {
				List resources = new ArrayList<>();
				// app-itmayiedu-order
				resources.add(swaggerResource("服务提供者-会员服务", "/provider/v2/api-docs?userToken=fupinxin144&password=admin", "2.0"));
				resources.add(swaggerResource("服务消费者-订单服务", "/consumer/v2/api-docs?userToken=fupinxin144&password=admin", "2.0"));
				return resources;
			}

			private SwaggerResource swaggerResource(String name, String location, String version) {
				SwaggerResource swaggerResource = new SwaggerResource();
				swaggerResource.setName(name);
				swaggerResource.setLocation(location);
				swaggerResource.setSwaggerVersion(version);
				return swaggerResource;
			}
		}

}
