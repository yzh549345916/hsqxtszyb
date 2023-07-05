package com.yzh.hsqxtszyb.model.ZNWG;

import com.yzh.hsqxtszyb.model.huanbao.DuoYuanRongHeSK;
import com.yzh.hsqxtszyb.model.huanbao.YBJYSkHour;

import java.util.List;

public class DuoYuanRongHeJoinSK1h extends DuoYuanRongHeSK {
    private List<YBJYSkHour> skList;

    public List<YBJYSkHour> getSkList() {
        return skList;
    }

    public void setSkList(List<YBJYSkHour> skList) {
        this.skList = skList;
    }
}
