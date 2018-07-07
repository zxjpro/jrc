package com.xiaojiezhu.jrc.client.coord;

import com.xiaojiezhu.jrc.client.coord.load.*;
import com.xiaojiezhu.jrc.client.coord.load.EnvCoordLoader;
import com.xiaojiezhu.jrc.client.coord.load.PropertiesCoordLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaojie.zhu
 */
public class DefaultCoordLoader implements CoordLoader {

    private List<CoordLoader> linkCoordLoader;
    public DefaultCoordLoader(){
        CoordLoader envLoader = new EnvCoordLoader();
        CoordLoader diskLoader = new DiskCoordLoader();
        CoordLoader propertiesLoader = new PropertiesCoordLoader();
        CoordLoader classpathLoader = new ClasspathCoordLoader();

        linkCoordLoader = new ArrayList<>();
        linkCoordLoader.add(envLoader);
        linkCoordLoader.add(diskLoader);
        linkCoordLoader.add(propertiesLoader);
        linkCoordLoader.add(classpathLoader);
    }

    @Override
    public String getGroup() {
        for(int i = 0; i < linkCoordLoader.size() ; i ++){
            String group = linkCoordLoader.get(i).getGroup();
            if(group != null){
                return group;
            }
        }
        return null;
    }

    @Override
    public String getUnit() {
        for(int i = 0; i < linkCoordLoader.size() ; i ++){
            String unit = linkCoordLoader.get(i).getUnit();
            if(unit != null){
                return unit;
            }
        }
        return null;
    }

    @Override
    public String getVersion() {
        for(int i = 0; i < linkCoordLoader.size() ; i ++){
            String version = linkCoordLoader.get(i).getVersion();
            if(version != null){
                return version;
            }
        }
        return null;
    }

    @Override
    public String getProfile() {
        for(int i = 0; i < linkCoordLoader.size() ; i ++){
            String profile = linkCoordLoader.get(i).getProfile();
            if(profile != null){
                return profile;
            }
        }
        return null;
    }
}
