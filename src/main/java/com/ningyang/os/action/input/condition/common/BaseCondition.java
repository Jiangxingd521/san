package com.ningyang.os.action.input.condition.common;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author： kaider
 * @Date：2018/08/30 17:22
 * @描述：
 */
@Data
public class BaseCondition implements Serializable {
    //页数
    private int page = 0;
    //条数
    private int limit = 10;

    public BaseCondition() {

    }

    public BaseCondition(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getPage() {
        if (page == 0) {
            page = 1;
        }
        return (page - 1) * limit;
    }

    public int getLimit() {
        return limit;
    }

    public Integer getCurrentPage() {
        Integer currentPage = this.page;
        if (currentPage == null || currentPage == 0) {
            return 1;
        }
        return currentPage;
    }
}
