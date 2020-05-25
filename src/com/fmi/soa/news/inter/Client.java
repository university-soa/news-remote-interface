package com.fmi.soa.news.inter;

import com.fmi.covid19.news.NewsService;
import com.fmi.covid19.news.RemoteNews;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import java.util.List;

@Configuration
@SpringBootApplication
public class Client {

    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8081/remote-news");
        invoker.setServiceInterface(NewsService.class);
        return invoker;
    }

    public static void main(String[] args) {
        NewsService service = SpringApplication
                .run(Client.class, args)
                .getBean(NewsService.class);
        List<RemoteNews> test = (List<RemoteNews>) service.getAllNews();
        for (RemoteNews remoteNews : test) {
            System.out.println(remoteNews);
        }

    }
}