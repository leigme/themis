package me.leig.project.themis.bean;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public enum ThemisFileType {

    DRL("drl"), DRT("drt");

    ThemisFileType(String type) {
    }

    public static ThemisFileType getType(String type) {
        switch (type) {
            case "drl":
                return ThemisFileType.DRL;
            case "drt":
                return ThemisFileType.DRT;
            default:
                return null;
        }
    }
}
