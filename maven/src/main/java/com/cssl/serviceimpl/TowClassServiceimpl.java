package com.cssl.serviceimpl;

import com.cssl.dao.TowClassMapper;
import com.cssl.service.TowClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TowClassServiceimpl implements TowClassService {
    @Autowired
    private TowClassMapper towClassMapper;

    @Override
    public List<Map<String, Object>> selthreelist(int towid) {
        return towClassMapper.selthreelist(towid);
    }
}
