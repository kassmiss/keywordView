package com.grlife.keyword_view;

import com.grlife.keyword_view.repository.KeywordRepository;
import com.grlife.keyword_view.repository.KeywordRepositoryImpl;
import com.grlife.keyword_view.service.KeywordService;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        Locale.setDefault(Locale.KOREA); // 위치 한국으로 설정 (한국어로 에러 메세지 나오게)
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setDefaultEncoding("UTF-8"); // 인코딩 설정
        messageSource.setBasenames("classpath:message/security_message", "classpath:org/springframework/security/messages"); // 커스텀한 properties 파일, security properties 파일 순서대로 설정
        return messageSource;
    }

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public KeywordService keywordService() {
        return new KeywordService(keywordRepository());
    }

    @Bean
    public KeywordRepository keywordRepository() {
        return new KeywordRepositoryImpl(dataSource);
    }

}
