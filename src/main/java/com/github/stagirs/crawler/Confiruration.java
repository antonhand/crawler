/*
 * Copyright 2017 Dmitriy Malakhov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.stagirs.crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author Dmitriy Malakhov
 */
@Component
public class Confiruration {
    private File confFile = new File(System.getProperty("catalina.home") + "/crawler/conf");
    private Map<String, String> config;
    
    @PostConstruct
    public void init(){
        try {
            if(confFile.exists()){
                config = new ObjectMapper().readValue(confFile, Map.class);
            }else{
                config = new HashMap<>();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void save(){
        try {
            new ObjectMapper().writeValue(confFile, config);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void set(String name, String value){
        config.put(name, value);
        save();
    }
    public String get(String name){
        return config.get(name);
    }
}
