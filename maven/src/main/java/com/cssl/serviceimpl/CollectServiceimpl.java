package com.cssl.serviceimpl;

import com.cssl.dao.CollectMapper;
import com.cssl.pojo.User_collect;
import com.cssl.pojo.Users;
import com.cssl.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class CollectServiceimpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public Users selectUsers(Integer uid) {
        return collectMapper.selectUsers(uid);
    }

    @Override
    public List<User_collect> selectAllShopProduct(Integer uid) {
        return collectMapper.selectAllShopProduct(uid);
    }

    @Override
    public List<Map<String, Object>> collectThing(Integer uid) {
        return collectMapper.collectThing(uid);
    }

    @Override
    public List<Map<String, Object>> selectBySouSuo(String cyname) {
        return collectMapper.selectBySouSuo(cyname);
    }
    //插入收藏
    @Override
    public int insertCollect(int c_pid, int u_id) {
        return collectMapper.insertCollect(c_pid,u_id);
    }
}
