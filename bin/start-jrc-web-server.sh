#/bin/bash

spring_config_location="-Dspring.config.location=config/jdbc.properties,config/cache.properties,jrc-web-server/config/application.yml"
#spring boot工程在加入maven项目后会自动配置，加上这段参数，则不会进入配置
disable_jrc="-Djrc.enable=false"

java $spring_config_location $disable_jrc -jar xx.jar