package com.br.tvlicai.firstchinanet.commom.utils;


import com.br.tvlicai.firstchinanet.commom.Const.Const;

import java.util.UUID;

/**
 * 获取ID的共通方法
 * Created by huhao on 2016/5/4.
 */
public class GetId {
    // ID的类型
    private char type;
    // ID
    private String id;

    public static String getId(char type) {
        // ID
        String id = Const.STR_EMPTY;
        if (type == Const.GET_ID_TYPE_1) {
            UUID uuid = UUID.randomUUID();
            id = uuid.toString();
        }
;        return id;
    }
}
