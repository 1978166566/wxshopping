package com.cssl.serviceimpl;

import com.cssl.dao.OneClassMapper;
import com.cssl.service.OneClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OneClassServiceimpl implements OneClassService{
   @Autowired
   private OneClassMapper oneClassMapper;
    @Override
    public List<Map<String, Object>> sellist() {
        return oneClassMapper.sellist();
    }

    @Override
    public List<Map<String, Object>> seltolist(int sid) {
        return oneClassMapper.seltolist(sid);
    }
}
