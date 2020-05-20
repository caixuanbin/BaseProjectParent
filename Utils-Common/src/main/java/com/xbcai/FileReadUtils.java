package com.xbcai;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
@RestController
public class FileReadUtils {

    @PostMapping("/check")
    public String check(@RequestParam("path") String path,
                        @RequestParam("timeday") String timeday,
                        @RequestParam("op") String op){
        listFile(path,timeday,op);
        return "OK";
    }
    //列出某个盘符下面所有的文件
    public  static void listFile(String path,String timeday,String op) {
        File dir = new File(path);
        File files[] = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listFile(file.getPath(),timeday,op);
            } else {
                System.out.println(file);
                if(file.getPath().toLowerCase().trim().contains(".csv")&&
                        file.getPath().toLowerCase().trim().contains(timeday)){
                    readFile(file.getPath().toLowerCase(),op);
                }
            }
        }
    }

    public  static void readFile(String filePath,String op){
        String tip = op.toLowerCase().trim();
        if(tip.equals(CommonEnum.TYPT.getValue())){//统一平台
            DataTyptCheckUtils.checkTYPT(filePath);
        }else if(tip.equals(CommonEnum.XFK.getValue())){//新服开
            DataXfkCheckUtils.checkTYPT(filePath);
        }
    }

}
