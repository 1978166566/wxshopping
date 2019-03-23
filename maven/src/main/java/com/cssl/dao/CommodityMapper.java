package com.cssl.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface CommodityMapper {
    /**
     * 分页查询卖场推荐
     * @return
     */
    public List<Map<String, String>> mall();

    /**
     * 修改商品的库存
     * @param cy_inventory
     * @param cy_id
     * @return
     */
    public int updateCom(int cy_inventory,int cy_id);

    /**
     * 修改库存前查询商品的数量
     * @param cy_id
     * @return
     */
    public int selCom(int cy_id);
    /**
     * 查询二级分类下的部分商品
     * @param oc_id
     * @return
     */
    public List<Map<String, String>> selTowPro(Integer oc_id);
    /**
     * 查询分类下的商品
     * @param figure 判断是几级分类
     * @param cid 分类id
     * @param pageno 当前页
     * @return
     */
    public Page<Map<String,Object>> selcomm(@Param("judge") int figure, @Param("c_id")int cid, int pageno, int pagesize);

    /**
     * 退货或者取消订单时返还库存
     * @param CY_INVENTORY 返还的库存数量
     * @param CY_ID 商品id
     * @return
     */
    public int updaquantity(@Param("CY_INVENTORY") int CY_INVENTORY,@Param("cy_id") int CY_ID);
}
