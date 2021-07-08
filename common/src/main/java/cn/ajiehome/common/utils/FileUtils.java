package cn.ajiehome.common.utils;

import cn.ajiehome.common.enums.CodeType;
import cn.ajiehome.common.exception.ApplicationException;
import cn.ajiehome.common.utils.entity.bt.ResultBT;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static ResultBT uploadFile(MultipartFile multipartFile, String filePath, Long fileName) {
        String contentType = multipartFile.getContentType();
        String fileType = contentType.substring(contentType.indexOf("/") + 1, contentType.length());
        String file = fileName + "." + fileType;
        File newFile = new File(filePath + file);
        ResultBT resultBT = new ResultBT();
        try {
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            multipartFile.transferTo(newFile);
            resultBT.setCode(200);
            resultBT.setResult("/image/" + file);
        } catch (IOException e) {
            throw new ApplicationException(CodeType.SERVICE_IO_ERROR);
        }
        return resultBT;
    }
}
