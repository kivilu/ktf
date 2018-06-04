package com.kivi.framework.constant.enums;

public enum KtfStatus {
    ENABLED( 0, "enabled" ),
    LOCKED( 1, "locked" ),
    DISABLED( 2, "disabled" ),
    PROCESSING( 3, "processing" ),
    DELETED( 4, "deleted" ),
    FREEZED( 5, "freezed" ),
    INIT( 9, "init" );

    private KtfStatus( int code, String desc ) {
        this.code = code;
        this.desc = desc;
    }

    public int    code;
    public String desc;

    public static String valueOf( Integer status ) {
        if (status == null) {
            return "";
        }
        else {
            for (KtfStatus s : KtfStatus.values()) {
                if (s.code == status) {
                    return s.desc;
                }
            }
            return "";
        }
    }

}
