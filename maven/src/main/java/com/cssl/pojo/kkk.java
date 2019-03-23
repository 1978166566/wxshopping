package com.cssl.pojo;

import lombok.Getter;
import lombok.Setter;
import org.apache.solr.client.solrj.beans.Field;

@Setter
@Getter
public class kkk {
    @Field
    private String id;
    @Field
    private String cy_name;
    @Field
    private String cy_price;
    @Field
    private String cy_sales;
    @Field
    private String p_image;
}
