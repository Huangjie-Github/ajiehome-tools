package cn.ajiehome.common.jwt.bo;

import lombok.Data;

/**
 * @Author: HuangJie
 * @Date: 2020/3/27 9:19
 * @Version: 1.0V
 */
@Data
public class JwtBeanBO {
    private Long id;
    private String userName;
    private String passWord;
    private String userPhone;
    private String userAddress;
}
