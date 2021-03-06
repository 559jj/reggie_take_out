package com.zjj.reggie.config;

import com.zjj.reggie.common.JacksonObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override//重写父类中的方法来设置静态资源映射
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("开始进行静态资源映射....");
        //将浏览器的访问路径映射到本地资源路径中
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

    //消息转换器会调用对象转换器处理java对象转json 对象转换器中可以添加将lang类型转换为string的序列化器
    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //创建新的消息转换器
        MappingJackson2HttpMessageConverter messageConverter=new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用jackson将java转成json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将我们常见的消息转换器对象追加到mvc框架的转换器集合并添加到第一位
        converters.add(0,messageConverter);
    }

}
