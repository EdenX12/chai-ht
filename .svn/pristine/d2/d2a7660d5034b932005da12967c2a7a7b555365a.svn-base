package com.tian.sakura.cdd.srv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 *
 * @author lvzonggang
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .enableUrlTemplating(true)
//                .select()
//                // 为当前包路径
//                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("CDD APIs")
//                .version("1.0")
//                .build();
//    }

    @Bean
    public Docket createBaseDataApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("基础数据","","1.0"))

                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.base"))
                .paths(PathSelectors.any())
                .build()
                .groupName("基础数据-api")
                .pathMapping("/")
                ;
    }

    @Bean
    public Docket createDemoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("测试模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.demo"))
                .paths(PathSelectors.any())
                .build()
                .groupName("测试模块-api")
                .pathMapping("/")
                ;
    }


    @Bean
    public Docket createLoginApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("登录模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.login"))
                .paths(PathSelectors.any())
                .build()
                .groupName("登录模块-api")
                .pathMapping("/");
    }

    @Bean
    public Docket createMyTaskApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("我的任务模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.mytask"))
                .paths(PathSelectors.any())
                .build()
                .groupName("任务模块-api")
                .pathMapping("/")
                ;
    }

    @Bean
    public Docket createTaskApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("任务金模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.task"))
                .paths(PathSelectors.any())
                .build()
                .groupName("任务金模块-api")
                .pathMapping("/")
                ;
    }

    @Bean
    public Docket createOrderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("订单模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.order"))
                .paths(PathSelectors.any())
                .build()
                .groupName("订单模块-api")
                .pathMapping("/");
    }



    @Bean
    public Docket createProductApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("产品模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.product"))
                .paths(PathSelectors.any())
                .build()
                .groupName("产品模块-api")
                .pathMapping("/")
                ;
    }

    @Bean
    public Docket createShopApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("店铺模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.shop"))
                .paths(PathSelectors.any())
                .build()
                .groupName("店铺模块-api")
                .pathMapping("/")
                ;
    }

    @Bean
    public Docket createUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("用户模块-api","","1.0"))
                .select()
                // 为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.tian.sakura.cdd.srv.web.user"))
                .paths(PathSelectors.any())
                .build()
                .groupName("用户模块-api")
                .pathMapping("/")
                ;
    }

    private ApiInfo apiInfo(String name, String description, String version) {
        return new ApiInfoBuilder().title(name).description(description).version(version).build();
    }

}
