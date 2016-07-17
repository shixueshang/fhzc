package com.fhzc.app.system.commons.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * Created by lihongde on 2016/7/16 0016.
 */
public class FileUtil {
    private static Logger logger  = LoggerFactory.getLogger(FileUtil.class);

    public static String generatePictureName(MultipartFile file) {
        String originName = file.getOriginalFilename();
        String suffix = originName.substring(originName.lastIndexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + suffix;
    }

    public static void transferFile(String path, String fileName, MultipartFile file) {
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            logger.error("保存文件失败");
            e.printStackTrace();
        }
    }
}