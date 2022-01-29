package hello.core;

import hello.core.filter.BaseFilter;
import hello.core.interceptor.BaseInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.Filter;
import javax.sql.DataSource;

@Configuration
@EnableWebMvc // for mvc
@MapperScan(value = "hello.core.mapper", sqlSessionFactoryRef = "sqlSessionFactory") // for mybatis mapper
@EnableTransactionManagement // for transaction manager
public class AppConfig implements WebMvcConfigurer {

    // jdbc config
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    // mybatis config
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

        Resource myBatisConfig = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml");
        sessionFactory.setConfigLocation(myBatisConfig);

        return sessionFactory.getObject();
    }

    @Bean(name = "SessionTemplate")
    public SqlSessionTemplate SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**"); // 정적 리소스 제외
    }

    @Bean
    public FilterRegistrationBean baseFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new BaseFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    @Override // 정적 리소스 mapping
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**") // url
                .addResourceLocations("classpath:/static/"); // 찾을 경로
    }
}