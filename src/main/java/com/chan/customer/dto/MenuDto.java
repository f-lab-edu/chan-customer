package com.chan.customer.dto;

import com.chan.customer.domain.Menu;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class MenuDto {

    private Long menuNo;

    @NotEmpty
    private String menuName;

    @NotEmpty
    @Size(min=7, max=7)
    private String menuPlan;

    @Min(0)
    private int menuPrice;

    @Min(1)
    private int menuCount;

    public Menu toEntity(){
        Menu menu = new Menu();

        menu.setMenuNo(this.menuNo);
        menu.setMenuName((this.menuName));
        menu.setMenuPlan(this.menuPlan);
        menu.setMenuPrice(menuPrice);
        menu.setMenuCount(menuCount);

        return menu;
    }
}
