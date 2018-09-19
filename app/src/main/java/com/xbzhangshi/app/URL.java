package com.xbzhangshi.app;

public class URL {

     public  final  static String BASE_PNG_URL ="http://xbzhanshi.com";
     public  final  static String BASE_URL ="http://xbzhanshi.com/native";
     public  final  static String BASE_END =".do";
     public  final  static String notice ="/notice"+BASE_END;//公告
     public  final  static String Loctterys ="/getLoctterys"+BASE_END;//可以彩种
     public  final  static String DatasBesidesLotterys ="/getDatasBesidesLotterys"+BASE_END;//获取体育，电子，真人分类的种类信息
     public  final  static String lottert_png ="http://xbzhanshi.com/mobile/v3/images/lottery/";//获取体育，电子，真人分类的种类信息
     public  final  static String LotterysCountDown ="/getLotterysCountDown"+BASE_END;//获取体育，电子，真人分类的种类信息

     /**
      * 特殊请求
      * http://xbzhanshi.com/mobile/v3/lotterys.do?lotCode=LHC&version=2&needLast=true
      */
     public final static  String ServiceTime1 ="http://xbzhanshi.com/mobile/v3/lotterys.do?lotCode=";
     public final static  String ServiceTime2 ="&version=2&needLast=true";

}
