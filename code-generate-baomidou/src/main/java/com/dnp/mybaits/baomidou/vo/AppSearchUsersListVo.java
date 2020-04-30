package com.dnp.mybaits.baomidou.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.AbstractList;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author huazai
 * @since 2019-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AppSearchUsersListVo", description = "位置分组list对象")
public class AppSearchUsersListVo extends AbstractList<AppUsersVo> {
    @Override
    public AppUsersVo get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
