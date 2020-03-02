package com.travanleo.user.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class DozerMapperConfiguration {

  @Bean(name = "dozerWithMapping")
  public DozerBeanMapper mapper(
    @Value(value = "classpath*:mapping/*.xml") Resource[] resourceArray) throws IOException {
    List<String> mappingFileUrlList = new ArrayList<>();
    for (Resource resource : resourceArray) {
      mappingFileUrlList.add(String.valueOf(resource.getURL()));
    }
    mappingFileUrlList.add("dozerJdk8Converters.xml");
    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
    dozerBeanMapper.setCustomFieldMapper(new HibernateInitializedFieldMapper());
    dozerBeanMapper.setMappingFiles(mappingFileUrlList);
    return dozerBeanMapper;
  }

}
