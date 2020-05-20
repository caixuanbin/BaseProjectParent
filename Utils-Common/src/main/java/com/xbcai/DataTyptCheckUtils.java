package com.xbcai;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 统一平台内容校验
 */
public class DataTyptCheckUtils {
    public static void  checkTYPT(String filePath){
        BufferedReader bReader =null;
        PrintWriter pWriter =null;
        try {
            System.out.println("开始解析文件："+filePath);
            File mfile = new File(filePath);
            String fileName = mfile.getName();
            String checkFilePath = filePath.substring(0,filePath.lastIndexOf(fileName));
            String checkResultPath = checkFilePath+"result\\typt\\"+
                    new SimpleDateFormat("yyyyMMdd").format(new Date())+"\\"+
                    fileName.substring(0,fileName.lastIndexOf(".csv"))+"_result.txt";
            //创建一个nextfile目录
            File nextFile = new File(checkFilePath,"result\\typt\\"+ new SimpleDateFormat("yyyyMMdd").format(new Date()));
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
            //检验 T_QYMP_114P 商家表
            if(file.contains("t_qymp_114p_typt_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=98){
                        sb.append("第"+no+"行有"+lineArray.length+"列,与实际要求的98列不相符，该列内容如下："+line+"\n");
                                no++;
                                continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                            e.printStackTrace();
                            }
                        }else if(i==7){//第8列 sms_id
                            if(StringUtils.isNotEmpty(lineArray[65])&&lineArray[65].equals("0")) {//基础版
                                if(column==null|| StringUtils.isEmpty(column.trim())){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                }
                            }

                        }else if(i==16){//第17列 Industry
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是Industry字段值为空\n");
                            }
                        }else if(i==31){//第32列 lng
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为0\n");
                            }
                        }else if(i==32){//第33列
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为0\n");
                            }
                        }else if(i==75){//第76列 qr_code_img
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是qr_code_img字段值为空\n");
                            }
                        }else if(i==80){//第81列 buid
                            if(StringUtils.isNotEmpty(lineArray[65])&&lineArray[65].equals("1")){//行业版
                                if(column==null||StringUtils.isEmpty(column.trim())){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                }
                            }
                        }
                    }
                    no++;
                }
            }
            //t_qymp_114p_del 删除的商家表
            else if(file.contains("t_qymp_114p_del_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=95){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的95列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==2){//第3列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==9){//第10列 sms_id
                            if(StringUtils.isNotEmpty(lineArray[67])&&lineArray[67].trim().equals("0")){//基础版
                                if(column==null||StringUtils.isEmpty(column.trim())){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                }
                            }
                        }else if(i==11){//第12列 city
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是city字段值为空\n");
                            }
                        }else if(i==18){//第19列 Industry
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是Industry字段值为空\n");
                            }
                        }else if(i==33){//第34列 lng
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为0\n");
                            }
                        }else if(i==34){//第35列 lat
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为0\n");
                            }
                        }else if(i==67){//第68列 edition
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                            }
                        }else if(i==77){//第78列 qr_code_img
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是qr_code_img字段值为空\n");
                            }
                        }else if(i==82){//第83列 buid
                            if(lineArray[67].equals("1")){//行业版
                                if(column==null||StringUtils.isEmpty(column.trim())){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                }
                            }
                        }
                    }
                    no++;
                }
            }
            //t_qymp_114p_temp 商家临时表
            else if(file.contains("t_qymp_114p_temp_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=99){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的99列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==7){//第8列 sms_id
                            if(StringUtils.isNotEmpty(lineArray[66])&&lineArray[66].trim().equals("0")){//基础版
                                if(column==null||StringUtils.isEmpty(column.trim())){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                }
                            }
                        }else if(i==9){//第10列 city
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是city字段值为空\n");
                            }
                        }else if(i==16){//第17列 Industry
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是Industry字段值为空\n");
                            }
                        }else if(i==30){//第31列 lng
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lng字段值为0\n");
                            }
                        }else if(i==31){//第32列 lat
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是lat字段值为0\n");
                            }
                        }else if(i==63){//第64列 site_url
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是site_url字段值为空\n");
                            }
                        }else if(i==66){//第67列 edition
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                            }
                        }else if(i==76){//第77列 qr_code_img
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是qr_code_img字段值为空\n");
                            }
                        }else if(i==81){//第82列 buid
                            if(StringUtils.isNotEmpty(lineArray[66])&&lineArray[66].trim().equals("1")){//行业版
                                if(column==null||StringUtils.isEmpty(column.trim())){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                }
                            }
                        }
                    }
                    no++;
                }
            }//t_qymp_114p_temp_extra 商家扩展表
            else if(file.contains("t_qymp_114p_temp_extra_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=27){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的27列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 buid
                                if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[2])){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"和3列发生错误，错误原因是buid及sms_id字段值为空\n");
                                }
                        }else if(i==20){//第21列 new_lng
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lng字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lng字段值为0\n");
                            }
                        }else if(i==21){//第22列 new_lat
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lat字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lat字段值为0\n");
                            }
                        }else if(i==23){//第24列 industry_id
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_id字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_114P_TEMP_EXTRA_DEL商家扩展删除表
            else if(file.contains("t_qymp_114p_temp_extra_del_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=27){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的27列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==2){//第3列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==3){//第4列 buid
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[2])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和5列发生错误，错误原因是buid及sms_id字段值为空\n");
                            }
                        }else if(i==22){//第23列 new_lng
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lng字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lng字段值为0\n");
                            }
                        }else if(i==23){//第24列 new_lat
                            if(column==null||StringUtils.isEmpty(column.trim())){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lat字段值为空\n");
                            }else if(column.trim().equals("0")){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是new_lat字段值为0\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_114P_TEMP_STATUS_CHANGE商家状态变化表
            else if(file.contains("t_qymp_114p_temp_status_change_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=16){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的16列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 sms_id
                            if(StringUtils.isNotEmpty(lineArray[6])&&lineArray[6].trim().equals("0")){//基础版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                }
                            }
                        }else if(i==4){//第5列 buid
                            if(StringUtils.isNotEmpty(lineArray[6])&&lineArray[6].trim().equals("1")){//行业版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                }
                            }
                        }else if(i==6){//第7列 edition
                            if(StringUtils.isEmpty(lineArray[6])){//行业版
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_AUDIT_DATA_SEND_COOPERATOR行业版商家审核结果发送合作商表
            else if(file.contains("t_qymp_114p_temp_status_change_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=10){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的10列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==2){//第3列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[3])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和4列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_INDUSTRY_DATA_SEND_ICRM行业版审核结果发送icrm表
            else if(file.contains("t_qymp_industry_data_send_icrm_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=6){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的6列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==4){//第5列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[5])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和4列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_INDUSTRY_DETAIL细分行业表
            else if(file.contains("t_qymp_industry_detail_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=6){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的6列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 industry_id
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_id字段值为空\n");
                            }
                        }else if(i==2){//第3列 parent_id
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是parent_id字段值为空\n");
                            }
                        }else if(i==3){//第4列 industry_name
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry_name字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_JOBS行业工单表
            else if(file.contains("t_qymp_jobs_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=20){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的20列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==3){//第4列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[15])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和16列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==4){//第5列 job_id
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是job_id字段值为空\n");
                            }
                        }else if(i==5){//第6列 status
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是status字段值为空\n");
                            }
                        }else if(i==6){//第7列 package_price
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是package_price字段值为空\n");
                            }
                        }else if(i==7){//第8列 customer_id
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是customer_id字段值为空\n");
                            }
                        }else if(i==8){//第9列 region
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是region字段值为空\n");
                            }
                        }else if(i==9){//第10列 industry
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_JOBS_DEL行业表工单拆机表
            else if(file.contains("t_qymp_jobs_del_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=22){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的22列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==2){//第3列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==5){//第6列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[17])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和16列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==6){//第7列 job_id
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是job_id字段值为空\n");
                            }
                        }else if(i==7){//第8列 status
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是status字段值为空\n");
                            }
                        }else if(i==8){//第9列 package_price
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是package_price字段值为空\n");
                            }
                        }else if(i==9){//第10列 customer_id
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是customer_id字段值为空\n");
                            }
                        }else if(i==10){//第11列 region
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是region字段值为空\n");
                            }
                        }else if(i==11){//第12列 industry
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是industry字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_JOBS_STATUS_CHANGE行业表工单状态变化表
            else if(file.contains("t_qymp_jobs_status_change_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=15){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的15列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[5])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和16列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==2){//第3列 job_id
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是job_id字段值为空\n");
                            }
                        }else if(i==3){//第4列 job_status
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是job_status字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_REPORT_LOGIN商家登录微站统计表
            else if(file.contains("t_qymp_report_login_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=4){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的4列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 date
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是date字段值为空\n");
                            }
                        }else if(i==1){//第2列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[3])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和16列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==2){//第3列 login_times
                            if(column==null||StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是login_times字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_SEND_MESSAGE_RECORD营销短信发送记录表
            else if(file.contains("t_qymp_send_message_record_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=12){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的12列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[1])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和2列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==6){//第7列 edition
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和16列发生错误，错误原因是edition字段值为空\n");
                            }
                        }else if(i==9){//第10列 city
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是city字段值为空\n");
                            }
                        }else if(i==11){//第12列 result_code
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是result_code字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_SMS短信内容表
            else if(file.contains("t_qymp_sms_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=24){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的24列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 cooperator
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                            }
                        }else if(i==2){//第3列 send_cooperator_status
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是send_cooperator_status字段值为空\n");
                            }
                        }else if(i==4){//第5列 sms_id
                            if(StringUtils.isNotEmpty(lineArray[16])&&lineArray[16].trim().equals("0")){//基础版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                }
                            }
                        }else if(i==5){//第6列 buid
                            if(StringUtils.isNotEmpty(lineArray[16])&&lineArray[16].trim().equals("1")){//行业版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                }
                            }
                        }else if(i==16){//第17列 edition
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_SMS_TEMPLATE短信模板表
            else if(file.contains("t_qymp_sms_template_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=14){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的14列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 cooperator
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                            }
                        }else if(i==2){//第3列 sms_id
                            if(StringUtils.isNotEmpty(lineArray[16])&&lineArray[11].trim().equals("0")){//基础版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是sms_id字段值为空\n");
                                }
                            }
                        }else if(i==3){//第4列 buid
                            if(StringUtils.isNotEmpty(lineArray[16])&&lineArray[11].trim().equals("1")){//行业版
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是buid字段值为空\n");
                                }
                            }
                        }else if(i==4){//第5列 content
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是content字段值为空\n");
                            }
                        }else if(i==5){//第6列 params_num
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是params_num字段值为空\n");
                            }
                        }else if(i==11){//第12列 edition
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是edition字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_WEB_BUILD_DATA并行施工接口数据发送表
            else if(file.contains("t_qymp_web_build_data_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=12){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的14列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[2])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和3列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==3){//第4列 send_state
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是send_state字段值为空\n");
                            }
                        }else if(i==9){//第10列 cooperator
                                if(StringUtils.isEmpty(column)){
                                    sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                                }
                        }else if(i==10){//第11列 interface_type
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是interface_type字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_WEB_BUILD_DATA_SECOND_SEND  并行施工接口二次下发表
            else if(file.contains("t_qymp_web_build_data_second_send_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=12){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的14列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[2])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和3列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==3){//第4列 send_state
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是send_state字段值为空\n");
                            }
                        }else if(i==9){//第10列 cooperator
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                            }
                        }else if(i==10){//第11列 interface_type
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是interface_type字段值为空\n");
                            }
                        }
                    }
                    no++;
                }
            }
            //T_QYMP_WEB_BUILD_DATA_SECOND_SEND_ID并行施工接口需要生成二次下发数据的id表
            else if(file.contains("t_qymp_web_build_data_second_typt")&&file.contains(".csv")) {
                while ((line = bReader.readLine()) != null) {
                    String[] lineArray = StringUtils.splitPreserveAllTokens(line, "|");
                    if(lineArray.length!=9){
                        sb.append("第"+no+"行有"+lineArray.length+"列，与实际要求的9列不相符，该列内容如下："+line+"\n");
                        no++;
                        continue;
                    }
                    for(int i=0;i<lineArray.length;i++){
                        String column=lineArray[i];
                        if(i==0){//第1列 ID
                            try {
                                Integer.valueOf(column);
                            } catch (NumberFormatException e) {
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是ID字段值为非数字\n");
                                e.printStackTrace();
                            }
                        }else if(i==1){//第2列 sms_id
                            if(StringUtils.isEmpty(column)&&StringUtils.isEmpty(lineArray[2])){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"和3列发生错误，错误原因是sms_id及buid字段值为空\n");
                            }
                        }else if(i==3){//第4列 cooperator
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是cooperator字段值为空\n");
                            }
                        }else if(i==4){//第5列 interface_type
                            if(StringUtils.isEmpty(column)){
                                sb.append("第"+no+"行 "+"第"+(i+1)+"列发生错误，错误原因是interface_type字段值为空\n");
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
