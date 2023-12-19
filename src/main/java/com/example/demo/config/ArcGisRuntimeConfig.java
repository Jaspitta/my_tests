package com.example.demo.config;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.loadable.LoadStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


//@Configuration
@Log4j2
public class ArcGisRuntimeConfig {

  @Bean
  @Primary
  public void setArcGisEnv() {
    ArcGISRuntimeEnvironment.initialize();
    ArcGISRuntimeEnvironment.setApiKey("");
  }

}