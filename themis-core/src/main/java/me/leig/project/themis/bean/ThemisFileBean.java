package me.leig.project.themis.bean;

import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import me.leig.project.themis.exception.ThemisException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TODO
 *
 * @Author leig
 * @Date 2021/3/14
 **/
public class ThemisFileBean {

    private String fileName;

    private ThemisFileType type;

    private String content;

    public ThemisFileBean() {}

    /**
     * 通过文件构建文件对象
     *
     * @param file
     * @throws IOException
     */
    public ThemisFileBean(File file) throws IOException {
        this.fileName = file.getName();
        ThemisFileType type = ThemisFileType.getType(Files.getFileExtension(file.getAbsolutePath()));
        if (null == type) {
            throw new ThemisException("file type is error");
        }
        this.type = type;
        this.content = CharStreams.toString(new InputStreamReader(new FileInputStream(file)));
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ThemisFileType getType() {
        return type;
    }

    public void setType(ThemisFileType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
