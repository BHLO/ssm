package cn.itcast.service.impl;

import cn.itcast.dao.LogDao;
import cn.itcast.domain.SysLog;
import cn.itcast.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;
    @Override
    public void insertLog(SysLog log) {
        logDao.insertLog(log);
    }
}
