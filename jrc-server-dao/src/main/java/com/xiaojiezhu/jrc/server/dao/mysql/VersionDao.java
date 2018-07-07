package com.xiaojiezhu.jrc.server.dao.mysql;

import com.xiaojiezhu.jrc.model.UnitVersion;
import com.xiaojiezhu.jrc.model.Version;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author xiaojie.zhu
 */
public interface VersionDao {

    /**
     * find version by version and profile
     * @param group
     * @param unit
     * @param version
     * @param profile
     * @return
     */
    @Select("SELECT a.*,b.group_,b.unit from version a left join unit b on a.unit_id=b.id " +
            "WHERE b.group_=#{group} and b.unit=#{unit} and version_=#{version} and profile_=#{profile}")
    Version findVersion(@Param("group") String group, @Param("unit") String unit, @Param("version") String version, @Param("profile") String profile);

    @Select("insert into version(unit_id,enable_,version_,profile_,description,request_number,create_time,update_time) " +
            "values(${unitId},${enable},#{version},#{profile},#{description},0,now(),now())")
    void insertVersion(Version version);


    List<Version> listVersion(@Param("start") int start, @Param("size") int size, @Param("unitId")int unitId, @Param("version") String version,@Param("profile")String profile);

    long countVersion(@Param("unitId")int unitId,@Param("version") String version,@Param("profile")String profile);

    @Select("select * from version where id=${id}")
    Version findVersionById(@Param("id") int versionId);

    /**
     * update version table config data
     * @param versionId
     * @param configContent
     * @return
     */
    @Update("update version set content=#{configContent},config_type=${configType},update_time=now() where id=${id}")
    int updateVersionConfigContent(@Param("id") int versionId, @Param("configContent") String configContent,@Param("configType") int configType);

    List<UnitVersion> listUnitVersion(@Param("group") String group, @Param("unit") String unit, @Param("version") String version, @Param("profile") String profile, @Param("start") int start, @Param("size") int size);


    long countUnitVersion(@Param("group") String group, @Param("unit") String unit, @Param("version") String version, @Param("profile") String profile);

    @Select("select count(1) from version WHERE unit_id =${id}")
    int countVersionById(@Param("id") int id);

    /**
     * delete version config
     * @param id version table id
     */
    @Delete("delete from version where id=${id}")
    void deleteVersionConfig(@Param("id") int id);
}
