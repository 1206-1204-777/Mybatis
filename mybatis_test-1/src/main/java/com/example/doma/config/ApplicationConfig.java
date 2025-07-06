package com.example.doma.config; // 適切なパッケージ名に変更

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // これがSpringの設定クラスであることを示す
public class ApplicationConfig {

    @Bean // このメソッドが返すオブジェクトをSpringのBeanとして登録する
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // 必要であれば、ModelMapperの厳密な設定を行う（例: STRICTマッチング戦略）
        // modelMapper.getConfiguration().setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STRICT);
        return modelMapper;
    }
}