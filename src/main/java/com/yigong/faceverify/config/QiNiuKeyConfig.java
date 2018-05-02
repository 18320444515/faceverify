package com.yigong.faceverify.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tianyi
 * @date 2018-02-20 21:46
 */
@Data
@ConfigurationProperties(prefix = "qiniu")
@Component
public class QiNiuKeyConfig {

    public String AK;

    public String SK;

    public String spacename;
}
