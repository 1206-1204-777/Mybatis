package com.example.doma.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        
        // 厳密マッチング戦略を使用
        mapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        
        // UserEntity → UserResponse のカスタムマッピング
        mapper.createTypeMap(com.example.doma.entity.UserEntity.class, 
                           com.example.common.dto.response.UserResponse.class)
            .addMappings(mapping -> {
                // locationId フィールドはスキップ（location オブジェクトを優先）
                mapping.skip(com.example.common.dto.response.UserResponse::setLocationId);
            });
        
        return mapper;
    }
}