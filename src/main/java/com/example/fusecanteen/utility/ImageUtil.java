package com.example.fusecanteen.utility;



import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ImageUtil {

    private static ApplicationProperties applicationProperties = new ApplicationProperties();
    /*
     * Convert base64 string to bytes
     * */
    public static byte[] toBytes(String content) {
        return new Base64().decode(content);
    }
    public static Optional<Path> storeImage(String content, String name, String extension) {
        byte[] image = toBytes(content);
        Path imagePath = Paths.get(applicationProperties.getUploadDir() + name + "." + extension);
        try {
            Files.write(imagePath, image);
            return Optional.of(imagePath);
        } catch (IOException e) {
            return Optional.empty();
        }
    }
    public  static String getImagePath(String fileName,String type,String name){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//"+type+"//"+name;
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            filePath+="//"+fileName;
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/"+type+"/"+name;
                File file = new File(filePath);
                if(!file.exists()){
                    file.mkdirs();
                }
                filePath+="/"+fileName;
            }
        }
        return filePath;
    }

    public static void saveImage(String imagePath,byte[] image) throws IOException{
        try{
//          File file = new File(imagePath+".jpg");
            File file = new File(imagePath);
            file.delete();
        }catch(Exception e){
            e.printStackTrace();
        }
//      BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imagePath+".jpg"));
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imagePath));
        stream.write(image);
        stream.close();
    }


    public static String getReportPath(String fileName){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//"+"reports";
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            filePath+="//"+fileName;
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/"+"reports";
                File file = new File(filePath);
                if(!file.exists()){
                    file.mkdirs();
                }
                filePath+="/"+fileName;
            }
        }
        return filePath;
    }

    public static String getArsTicketPath(String fileName){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//"+"arstickets";
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            filePath+="//"+fileName;
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/"+"arstickets";
                File file = new File(filePath);
                if(!file.exists()){
                    file.mkdirs();
                }
                filePath+="/"+fileName;
            }
        }
        return filePath;
    }

    public static String getBasePath(String fileName, String folder){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//"+folder;
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            filePath+="//"+fileName;
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/"+folder;
                File file = new File(filePath);
                if(!file.exists()){
                    file.mkdirs();
                }
                filePath+="/"+fileName;
            }
        }
        return filePath;
    }

    public static String getBasePathOfService(String fileName, String folder){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//service//"+folder;
            File file = new File(filePath);
            if(!file.exists()){
                file.mkdirs();
            }
            filePath+="//"+fileName;
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/service/"+folder;
                File file = new File(filePath);
                if(!file.exists()){
                    file.mkdirs();
                }
                filePath+="/"+fileName;
            }
        }
        return filePath;
    }

    public static String getBasePath(String folder){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//"+folder+"//";
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/"+folder+"/";
            }
        }
        return filePath;
    }

    public static String getDealerDirectoryPath(String type , String folder){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//"+type+"//"+folder;
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/"+type+"/"+folder;
            }
        }
        return filePath;
    }
    public static String getReportSavingDirectory(String folderName){
        String filePath = "";
        if(SystemUtils.IS_OS_WINDOWS){
            filePath = StringConstants.OS_WINDOWS_DIRECTORY+"//"+folderName;
        }else{
            if(SystemUtils.IS_OS_LINUX){
                filePath = StringConstants.OS_LINUX_DIRECTORY+"/"+folderName;
            }
        }
        return filePath;
    }


}
