package com.fhzc.app.api.tools;

import com.fhzc.app.system.commons.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by lihongde on 2016/7/13.
 */
public class FileUtils {

    public static int DEFAULT_THUMBNAIL_WIDTH = 265;
    public static int DEFAULT_THUMBNAIL_HEIGHT = 265;

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static List<String> saveFilesToDisk(HttpServletRequest request, String savePath) {
        return saveFilesToDisk(request, savePath, false);
    }

    public static List<String> saveFilesToDisk(HttpServletRequest request,String savePath, boolean createThumbnail) {
        List<String> fileNameList = new ArrayList<String>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                String fileName = iter.next();
                List<MultipartFile> fileList = multiRequest.getFiles(fileName);
                for (MultipartFile file : fileList) {
                    if (file != null && file.getSize() > 0) {
                        String orginalFileName = file.getOriginalFilename();
                        String extName = TextUtils.getFileExtName(orginalFileName, "png");
                        String mainPart = UUID.randomUUID().toString();
                        String newFileName = mainPart + "." + extName;
                        String newPath = savePath + newFileName;
                        File localFile = new File(newPath);
                        if(!localFile.exists()){
                            localFile.mkdirs();
                        }
                        try {
                            file.transferTo(localFile);
                        } catch (IllegalStateException e) {
                            logger.error("上传图片出错 ");
                            e.printStackTrace();
                        } catch (IOException e) {
                            logger.error("上传图片出错");
                            e.printStackTrace();
                        }
                        fileNameList.add(newFileName);
                        logger.info("upload file done. path:" + newPath);

                        // createThumbnail
                        if (createThumbnail) {
                            String thumbnailImagePath = savePath + mainPart + "_" + DEFAULT_THUMBNAIL_WIDTH + "x" + DEFAULT_THUMBNAIL_HEIGHT + "." + extName;
                            ImageUtil.thumbnailImage(localFile, DEFAULT_THUMBNAIL_WIDTH, DEFAULT_THUMBNAIL_HEIGHT, thumbnailImagePath, false);
                            logger.info("create thumnail file done. path:" + thumbnailImagePath);
                        }
                    }
                }

            }
        }

        return fileNameList;

    }

    /**
     * 复制单个文件
     *
     * @param srcFileName
     *            待复制的文件名
     * @param destFileName
     *            目标文件名
     * @param overlay
     *            如果目标文件存在，是否覆盖
     * @return 如果复制成功返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String destFileName, boolean overlay) {
        File srcFile = new File(srcFileName);

        // 判断源文件是否存在
        if (!srcFile.exists()) {
            return false;
        } else if (!srcFile.isFile()) {
            return false;
        }

        // 判断目标文件是否存在
        File destFile = new File(destFileName);
        if (destFile.exists()) {
            // 如果目标文件存在并允许覆盖
            if (overlay) {
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件
                new File(destFileName).delete();
            }
        } else {
            // 如果目标文件所在目录不存在，则创建目录
            if (!destFile.getParentFile().exists()) {
                // 目标文件所在目录不存在
                if (!destFile.getParentFile().mkdirs()) {
                    // 复制文件失败：创建目标文件所在目录失败
                    return false;
                }
            }
        }

        // 复制文件
        int byteread = 0; // 读取的字节数
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];

            while ((byteread = in.read(buffer)) != -1) {
                out.write(buffer, 0, byteread);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建文件
     * @param destFileName
     * @return
     */
    public static void createFile(String destFileName) {
        File file = new File(destFileName);
        if(file.isFile() && file.exists()) {
            file.delete();
            logger.info("删除已经存在的文件");
        }
        if (destFileName.endsWith(File.separator)) {
            logger.info("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
        }
        if(!file.getParentFile().exists()) {
            if(!file.getParentFile().mkdirs()) {
                logger.info("创建目标文件所在目录失败！");
            }
        }
        try {
            if (file.createNewFile()) {
                System.out.println("创建单个文件" + destFileName + "成功！");
            } else {
                logger.info("创建单个文件" + destFileName + "失败！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("创建单个文件" + destFileName + "失败！" + e.getMessage());
        }
    }
}
