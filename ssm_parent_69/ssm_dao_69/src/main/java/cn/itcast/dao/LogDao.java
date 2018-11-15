package cn.itcast.dao;

import cn.itcast.domain.SysLog;
import cn.itcast.domain.SysRole;
import org.apache.ibatis.annotations.Insert;


public interface LogDao {
    @Insert("insert into sys_log values(common_sequence.nextval,#{visitTime},#{username}," +
            " #{ip},#{method},#{executeResult},#{executeMsg},#{executeTime})")
    void insertLog(SysLog log);
}
