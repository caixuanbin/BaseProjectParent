package com.xbcai;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 新服开内容校验
 */
public class DataXfkCheckUtils {
    public static void  checkTYPT(String filePath){
        BufferedReader bReader =null;
        PrintWriter pWriter =null;
        try {
            System.out.println("开始解析文件："+filePath);
            File mfile = new File(filePath);
            String fileName = mfile.getName();
            String checkFilePath = filePath.substring(0,filePath.lastIndexOf(fileName));
            String checkResultPath = checkFilePath+"result\\xkf\\"+
                    new SimpleDateFormat("yyyyMMdd").format(new Date())+"\\"+
                    fileName.substring(0,fileName.lastIndexOf(".csv"))+"_result.txt";
            //创建一个nextfile目录
            File nextFile = new File(checkFilePath,"result\\xkf\\"+ new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if(!nextFile.exists()){//如果不存在文件夹就创建文件夹
                nextFile.mkdirs();
                //mkdirs可以建立多级文件夹， 而mkdir()只能建立一级的文件夹，如果输入多级路径，则会返回false；
            }
            String file = filePath.toLowerCase();
            StringBuilder sb = new StringBuilder();
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
            pWriter = new PrintWriter(new FileWriter(checkResultPath),true);
            String line = null;
            int no=1;
            sb.append(filePath+" 解析内容结果如下：\n");
            //t_114sps_analyse_combine_order产品优惠合并表
            if(file.contains("t_114sps_analyse_combine_order_xfk")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=96){
                        sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的96列不相符，该列内容如下："+line+"\n");
                                no++;
                                continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(column==null){
                            column="";
                        }else{
                            column=column.trim();
                        }
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                            e.printStackTrace();
                            }
                        }else if(i==2){//第3列 source
                                if( StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是source字段值为空\n");
                                }
                        }else if(i==6){//第7列 status
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是status字段值为空\n");
                            }
                        }else if(i==17){//第18列 produtId
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是produtId字段值为空\n");
                            }
                        }else if(i==18){//第19列 productName
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是productName字段值为空\n");
                            }
                        }else if(i==22){//第23列 area_code
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是area_code字段值为空\n");
                            }
                        }else if(i==30){//第31列 cooperator
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                            }
                        }else if(i==38){//第39列 lat
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为空\n");
                            }else if(column.equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为0\n");
                            }
                        }else if(i==39){//第40列 lng
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为空\n");
                            }else if(column.equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为0\n");
                            }
                        }else if(i==56){//第57列 industry_class
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_class字段值为空\n");
                            }
                        }else if(i==57){//第58列 package_name
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是package_name字段值为空\n");
                            }
                        }else if(i==64){//第65列 buid
                            if(StringUtils.isNotEmpty(lineArray[73])&&lineArray[73].trim().equals("1")){//行业版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                }
                            }
                        }else if(i==65){//第66列 sms_id
                            if(StringUtils.isNotEmpty(lineArray[73])&&lineArray[73].trim().equals("0")){//基础版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                }
                            }
                        }else if(i==73){//第74列 edition
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                            }
                        }else if(i==79){//第80列 bte_industry
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是bte_industry字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
              //t_114sps_analyse_combine_order_his产品优惠合并历史表
             else if(file.contains("t_114sps_analyse_combine_order_his_xfk")&&file.contains(".csv")) {
                    while ((line = bReader.readLine()) != null) {
                        String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                        if(lineArray.length!=96){
                            sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的96列不相符，该列内容如下："+line+"\n");
                            no++;
                            continue;
                        }
                        for(int i=0;i<lineArray.length;i++){
                            String column=lineArray[i];
                            if(column==null){
                                column="";
                            }else{
                                column=column.trim();
                            }
                            if(i==0){//第1列 ID
                                try {
                                    Integer.valueOf(column);
                                } catch (NumberFormatException e) {
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                    e.printStackTrace();
                                }
                            }else if(i==2){//第3列 source
                                if( StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是source字段值为空\n");
                                }
                            }else if(i==6){//第7列 status
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是status字段值为空\n");
                                }
                            }else if(i==17){//第18列 produtId
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是produtId字段值为空\n");
                                }
                            }else if(i==18){//第19列 productName
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是productName字段值为空\n");
                                }
                            }else if(i==22){//第23列 area_code
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是area_code字段值为空\n");
                                }
                            }else if(i==30){//第31列 cooperator
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                                }
                            }else if(i==38){//第39列 lat
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为空\n");
                                }else if(column.equals("0")){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为0\n");
                                }
                            }else if(i==39){//第40列 lng
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为空\n");
                                }else if(column.equals("0")){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为0\n");
                                }
                            }else if(i==56){//第57列 industry_class
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_class字段值为空\n");
                                }
                            }else if(i==57){//第58列 package_name
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是package_name字段值为空\n");
                                }
                            }else if(i==64){//第65列 buid
                                if(StringUtils.isNotEmpty(lineArray[73])&&lineArray[73].trim().equals("1")){//行业版
                                    if(StringUtils.isEmpty(column)){
                                        sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                    }
                                }
                            }else if(i==65){//第66列 sms_id
                                if(StringUtils.isNotEmpty(lineArray[73])&&lineArray[73].trim().equals("0")){//基础版
                                    if(StringUtils.isEmpty(column)){
                                        sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                    }
                                }
                            }else if(i==73){//第74列 edition
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                                }
                            }else if(i==79){//第80列 bte_industry
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是bte_industry字段值为空\n");
                                }
                            }
                        }
                        no++;
                    }
                }

            //t_114sps_analyse_crm_data_entity 优惠工单表
            else if(file.contains("t_114sps_analyse_crm_data_entity_xfk")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=48){
                        sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的48列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(column==null){
                            column="";
                        }else{
                            column=column.trim();
                        }
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==5){//第6列 status
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是status字段值为空\n");
                            }
                        }else if(i==14){//第15列 product_id
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是product_id字段值为空\n");
                            }
                        }else if(i==15){//第16列 productName
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是productName字段值为空\n");
                            }
                        }else if(i==17){//第18列 area_code
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是area_code字段值为空\n");
                            }
                        }else if(i==22){//第23列 area_code
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是area_code字段值为空\n");
                            }
                        }else if(i==22){//第23列 order_status
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是order_status字段值为空\n");
                            }
                        }else if(i==24){//第25列 industry_class
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_class字段值为空\n");
                            }
                        }else if(i==29){//第30列 cooperator
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                            }
                        }else if(i==47){//第48列 edition
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //t_114sps_analyse_sps_data_entity产品信息表
            else if(file.contains("t_114sps_analyse_sps_data_entity_xfk")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=57){
                        sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的57列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(column==null){
                            column="";
                        }else{
                            column=column.trim();
                        }
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==5){//第6列 status
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是status字段值为空\n");
                            }
                        }else if(i==15){//第16列 product_id
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是product_id字段值为空\n");
                            }
                        }else if(i==16){//第17列 product_name
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是product_name字段值为空\n");
                            }
                        }else if(i==20){//第21列 area_code
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是area_code字段值为空\n");
                            }
                        }else if(i==25){//第26列 order_status
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是order_status字段值为空\n");
                            }
                        }else if(i==31){//第32列 cooperator
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                            }
                        }else if(i==46){//第47列 industry_class
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_class字段值为空\n");
                            }
                        }else if(i==47){//第48列 industry_class_s
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_class_s字段值为空\n");
                            }
                        }else if(i==48){//第49列 package_name
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是package_name字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //t_workorder_favorable_object_info优惠对象信息
            else if(file.contains("t_workorder_favorable_object_info_xfk")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=7){
                        sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的7列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(column==null){
                            column="";
                        }else{
                            column=column.trim();
                        }
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 order_id
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是order_id字段值为空\n");
                            }
                        }else if(i==2){//第3列 sale_item_id
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sale_item_id字段值为空\n");
                            }
                        }else if(i==3){//第4列 unit_phone
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是unit_phone字段值为空\n");
                            }
                        }else if(i==4){//第5列 product_id
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是product_id字段值为空\n");
                            }
                        }else if(i==5){//第6列 op
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是op字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //t_workorder_favorable_package_info优惠包信息
            else if(file.contains("t_workorder_favorable_package_info_xfk")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=13){
                        sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的13列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(column==null){
                            column="";
                        }else{
                            column=column.trim();
                        }
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 order_id
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是order_id字段值为空\n");
                            }
                        }else if(i==2){//第3列 sale_item_id
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sale_item_id字段值为空\n");
                            }
                        }else if(i==3){//第4列 sale_item_id
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sale_item_id字段值为空\n");
                            }
                        }else if(i==4){//第5列 effect_type
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是effect_type字段值为空\n");
                            }
                        }else if(i==5){//第6列 limit_type
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是limit_type字段值为空\n");
                            }
                        }else if(i==10){//第11列 op
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是op字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //t_workorder_info_attrs 产品属性
            else if(file.contains("t_workorder_info_attrs_xfk")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=5){
                        sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的5列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(column==null){
                            column="";
                        }else{
                            column=column.trim();
                        }
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 order_id
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是order_id字段值为空\n");
                            }
                        }else if(i==2){//第3列 attr_key
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是attr_key字段值为空\n");
                            }
                        }else if(i==3){//第4列 attr_value
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是attr_value字段值为空\n");
                            }
                        }else if(i==4){//第5列 attr_op
                            if( StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是attr_op字段值为空\n");
                            }
                        }

                    }
                    no++;
                }
            }
            sb.append("结束解析文件："+filePath);
            System.out.println(sb.toString());
            pWriter.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bReader.close();
                pWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) {
        checkTYPT("C:\\Users\\Administrator\\Desktop\\114\\data\\typt\\t_qymp_114p_typt_20180716_a.csv");
    }

}
