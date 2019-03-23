package com.cssl.serviceimpl;

import com.cssl.dao.ShoppingMapper;
import com.cssl.pojo.Shopping;
import com.cssl.pojo.Users;
import com.cssl.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShoppingServiceimpl implements ShoppingService {

     @Autowired
     private ShoppingMapper shoppingMapper;
    /**
     * 更改购物车商品数量
     * @param sp_quantity
     * @param sp_id
     * @return
     */
    @Override
    public int upShoppingNum(int sp_quantity, int sp_id) {
        return shoppingMapper.upShoppingNum(sp_quantity,sp_id);
    }
    /*
     *根据购物车id删除购物车记录
     * @param cy_id
     * @param u_id
     * @return
     */
    @Override
    public int delShoppingById(int sp_id) {
        return shoppingMapper.delShoppingById(sp_id);
    }
    /**
     * 查询某用户下的单个商品的数量
     * @param u_id
     * @param cy_id
     * @return
     */
    @Override
    public int selProSum(int u_id, int cy_id) {
        return shoppingMapper.selProSum(u_id,cy_id);
    }

    /**
     * 根据cookie购物车商品id查询商品信息
     * @param u_id
     * @return
     */
    @Override
    public List<Map<String, String>> selProByID(Integer u_id) {
        return shoppingMapper.selProByID(u_id);
    }

    /**
     * 查询该用户下的购物车详情
     * @param u_id
     * @return
     */
    @Override
    public List<Map<String, String>> selShoppingInfo(int u_id) {
        return shoppingMapper.selShoppingInfo(u_id);
    }

    /**
     * 插入购物车
     * @param shopping
     * @return
     */
    public int instShopping(Shopping shopping){
           return shoppingMapper.instShopping(shopping);
    }
    /**
     *查询指定用户下的商品
     * @param u_id
     * @return
     */
    @Override
    public List<Shopping> selShopping(int u_id) {
        return shoppingMapper.selShopping(u_id);
    }
}
