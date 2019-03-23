package com.cssl.serviceimpl;

import com.cssl.dao.CommodityMapper;
import com.cssl.service.CommodityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class CommodityServiceimpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    /**
     * 分页查询卖场推荐
     * @return
     */
    @Override
    public List<Map<String, String>> mall() {
        return commodityMapper.mall();
    }
    /**
     * 修改库存前查询商品的数量
     * @param cy_id
     * @return
     */
    @Override
    public int selCom(int cy_id) {
        return 0;
    }

    /**
     * 修改商品的库存
     * @param cy_inventory
     * @param cy_id
     * @return
     */
    @Override
    public int updateCom(int cy_inventory, int cy_id) {
        return commodityMapper.updateCom(cy_inventory,cy_id);
    }

    /**
     * 查询二级分类下的部分商品
     * @param oc_id
     * @return
     */
    @Override
    public List<Map<String, String>> selTowPro(Integer oc_id) {
        return commodityMapper.selTowPro(oc_id);
    }
    @Override
    public Page<Map<String, Object>> selcomm(int figure, int cid, int pageno) {
        Page<Map<String,Object>> page= PageHelper.startPage(pageno, 2);
        commodityMapper.selcomm(figure,cid,pageno,2);
        return page;
    }

    @Override
    public int updaquantity(int CY_INVENTORY, int CY_ID) {
        return commodityMapper.updaquantity(CY_INVENTORY,CY_ID);
    }
}
