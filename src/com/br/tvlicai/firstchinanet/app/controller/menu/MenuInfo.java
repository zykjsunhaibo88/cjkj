package com.br.tvlicai.firstchinanet.app.controller.menu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huhao on 2016/6/12.
 */
public class MenuInfo implements Serializable{

    private BrtCmnMenuMall fstMenu;

    private List<BrtCmnMenuMall> secMenu;

    public BrtCmnMenuMall getFstMenu() {
        return fstMenu;
    }

    public void setFstMenu(BrtCmnMenuMall fstMenu) {
        this.fstMenu = fstMenu;
    }

    public List<BrtCmnMenuMall> getSecMenu() {
        return secMenu;
    }

    public void setSecMenu(List<BrtCmnMenuMall> secMenu) {
        this.secMenu = secMenu;
    }
}
