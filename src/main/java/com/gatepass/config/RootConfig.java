package com.gatepass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistanceConfig.class)
public class RootConfig {

}
