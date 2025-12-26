package az.orient.eshopspring.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumStatus {

    ACTIVE(1), DEACTIV(0);

    private int value;

    public int getValue(){
        return this.value;
    }
}
