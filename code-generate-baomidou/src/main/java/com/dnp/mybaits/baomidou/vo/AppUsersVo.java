package com.dnp.mybaits.baomidou.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "AppUsersVo")
@XmlAccessorType(XmlAccessType.NONE)
@ApiModel(value = "AppUsersVo", description = "用户信息（userId）")
public class AppUsersVo implements Serializable {
    @XmlAttribute
    @ApiModelProperty(value = "用户id", dataType = "string")
    private String userId;
}
