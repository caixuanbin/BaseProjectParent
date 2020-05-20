package com.xbcai.test;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class FileReadUtils {
    //列出某个盘符下面所有的文件
    public static void listFile(String path,String timeday) {
        if(StringUtils.isEmpty(path)||StringUtils.isEmpty(timeday)){
            System.out.println("请输入文件路径、及时间");
            return;
        }
        File dir = new File(path);
        File files[] = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                listFile(file.getPath(),timeday);
            } else {
                System.out.println(file);
                if(file.getPath().toLowerCase().trim().contains(".csv")&&
                        file.getPath().toLowerCase().trim().contains(timeday)){
                    readFile(file.getPath().toLowerCase());
                }
            }
        }
    }

    public static void readFile(String filePath){
        DataCheckUtils.checkTYPT(filePath);
    }

    public static void main(String[] args) {
        listFile("C:\\Users\\Administrator\\Desktop\\114\\data\\typt","");
      //  File f = new File("C:\\Users\\Administrator\\Desktop\\114\\data\\typt\\t_qymp_114p_typt_20180716_a.csv");
       //System.out.println(f.getName().substring(0,f.getName().lastIndexOf(".csv")));

    }

}
