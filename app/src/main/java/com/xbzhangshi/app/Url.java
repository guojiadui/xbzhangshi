package com.xbzhangshi.app;

public class Url {

     public  final  static String BASE_PNG_URL ="http://xbzhanshi.com";
     public  final  static String BASE_URL ="http://xbzhanshi.com/native";
     public  final  static String BASE_END =".do";
     public  final  static String notice ="/notice"+BASE_END;//公告
     public  final  static String Loctterys ="/getLoctterys"+BASE_END;//可以彩种
     public  final  static String DatasBesidesLotterys ="/getDatasBesidesLotterys"+BASE_END;//获取体育，电子，真人分类的种类信息
     public  final  static String lottert_png ="http://xbzhanshi.com/mobile/v3/images/lottery/";//获取体育，电子，真人分类的种类信息
     public  final  static String LotterysCountDown ="/getLotterysCountDown"+BASE_END;//获取体育，电子，真人分类的种类信息
     public  final  static String login ="/login"+BASE_END;//登录
     public  final  static String accountInfo ="/accountInfo"+BASE_END;//获取用户信息
     public  final  static String reg_guest ="/reg_guest"+BASE_END;//获取免费账号
     public  final  static String meminfo ="/meminfo"+BASE_END;//余额
     public  final  static String logout ="/logout"+BASE_END;//登出
     public  final  static String register ="/register"+BASE_END;//注册
     public  final  static String regVerifycode ="/regVerifycode"+BASE_END;//验  证  码
     public  final  static String regconfig ="/regconfig"+BASE_END;//2.	获取注册界面配置信息
     public  final  static String getVip ="http://xbzhanshi.com/center/member/meminfo/mobieLevel.do";//2.	获取注册界面配置信息
     public  final  static String updateuserInfo ="/update_sysaccount"+BASE_END;//更新用户内容
     public  final  static String exchangeConfig ="/exchangeConfig"+BASE_END;//兑换配置
     public  final  static String exchange ="/exchange"+BASE_END;//兑换交换


     /**
      * 获取登录的验证码
      */
  public  final  static  String login_code="http://xbzhanshi.com/mobile/authnum.do";
     /**
      * 特殊请求
      * http://xbzhanshi.com/mobile/v3/lotterys.do?lotCode=LHC&version=2&needLast=true
      * 获取某一彩票的下一期时间和最新一期结果
      */
     public final static  String ServiceTime1 ="http://xbzhanshi.com/mobile/v3/lotterys.do?lotCode=";
     public final static  String ServiceTime2 ="&version=2&needLast=true";

     /**
      * 获取最新最新的所以彩票的结果，用于开奖公告
      */
     public final static  String OpenPrize ="http://xbzhanshi.com/mobile/v3/draw_notice_data.do";
     /**
      * 获取彩票结果列表
      * http://xbzhanshi.com/mobile/v3/draw_notice_details_data.do?lotCode=PCEGG&page=2&rows=10
      */
     public final static  String OpenPrizeResultList ="http://xbzhanshi.com/mobile/v3/draw_notice_details_data.do";
}
