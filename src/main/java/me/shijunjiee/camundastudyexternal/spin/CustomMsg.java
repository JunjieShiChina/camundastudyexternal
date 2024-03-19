package me.shijunjiee.camundastudyexternal.spin;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomMsg {

    private String name;
    private String product;
    private int productAge;
    private Date purchaseDate;
    private ProductType type;
    private List<String> repairSite;

}
