package com.xbcai.test;

import org.apache.commons.lang3.StringUtils;

public class Test {
    public static  String str="995920|001|1|2018-07-09 16:39:02.0|2018-07-09 16:47:46.0|0|30|402567999651*9550538236|FW907572568513*7065597399|0|3755056754780000|来泰丰眼镜有限公司|DXMP2156104520|SZ200000005455985229|GDSZ3755056754780000||0|1531126066050|||021|021|0755||1|28663883|28663883||001|18|xa|58||0|18620313672|陈亚群|||||||DSL7|移动互联网应用运营中心/号百信息服务中心|1228100903||ZH0002-024-1-1|6576195413|ZH0002-024-1-1、、6576195413、、04、、0、、0、、、、20180712、、21110101、、001、、ZH0002-024-1#$|10042269、、28663883、、1、、001#$10042269、、DXMP2156104520、、2407、、001#$||||||||114+企业名片行业版包月套餐,114+企业名片行业版包月套餐赠送3个月套餐外等额话费优惠||||PM_DJQYDZ、、龙岗区横岗街道温屋20号3层、、001#$PM_DJDHHM、、28663883、、001#$PM_DJQYYB、、518000、、001#$PM_DJQYMC、、来泰丰眼镜有限公司、、001#$PM_DLS、、DSL7、、001#$PM_DJKHXX、、、、001#$PM_HYLX、、制造及房地产业、、001#$BA_MSDEPTNAME、、12、、001#$BA_MSMAN、、深圳市新奥互联网有限公司、、001#$PM_DXMPJZDC、、1、、001#$PM_SLDYSLSH、、0、、001#$PM_YWSLLB、、SLLB01、、001#$PM_SRFJ、、4、、001#$PM_DJHMGS、、1、、001#$PM_BHHM、、28663883、、001#$PM_JFQ、、01、、001#$PM_JFJG、、1、、001#$PM_YZ、、30、、001#$PM_SFZDXY、、XY01、、001#$PM_DXFSSL、、100、、001#$PB_BILLINGTYPE、、000000、、005#$PB_USERTYPE、、100002、、005#$PB_USERCHAR、、JFSX01、、005#$BEGIN_DATE、、20180709、、005#$END_DATE、、20991231、、005#$|||0755-DXMP2156104520-1531126066050|153112606605029639235|1|2018-07-16 11:31:12.0|http://card.114station.com/domain?sms_id=153112606605029639235|http://t.cn/RgtzE7n|||0|1|0755|来泰丰眼镜有限公司|GDSZ633835793L|龙岗区横岗街道温屋20号3层|1|F06|1|28663883|1|29639235||sz+01+58+xa+sz03058xa001|来泰丰眼镜有限公司 电话:28663883 地址:龙岗区横岗街道温屋20号3层 点击http://t.cn/RgtzE7n 了解详情||王丽霞||system|2018-07-12 14:25:18.0|||0|bte校验失败，个人用户需补充资质文件";
    public static void main(String[] args) {
       String[] a = StringUtils.splitPreserveAllTokens(str,"|");
        for(int i=0;i<a.length;i++){
            System.out.println("第"+(i+1)+"列--->"+a[i]);
        }
       // System.out.println(StringUtils.splitPreserveAllTokens(str,"|").length);
       /** String file ="C:\\Users\\Administrator\\Desktop\\114\\data\\typt\\3f.csv";
        File f = new File(file);
        String fileName = f.getName();
        String rP =file.substring(0,file.lastIndexOf(f.getName()));
        System.out.println(file.substring(0,file.lastIndexOf(f.getName())));
        System.out.println(rP+"result\\"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"\\"+fileName.substring(0,fileName.lastIndexOf(".csv"))+"_result.txt");
**/

    }
}
