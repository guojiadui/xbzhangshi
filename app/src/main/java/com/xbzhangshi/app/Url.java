package com.xbzhangshi.app;

public class Url {

    public  final  static String APP_URL_HEAD ="http://xbzhanshi.com";

     public  final  static String BASE_URL =APP_URL_HEAD+"/native";
     public  final  static String BASE_END =".do";
     public  final  static String notice ="/notice"+BASE_END;//公告
     public  final  static String Loctterys ="/getLoctterys"+BASE_END;//可以彩种
     public  final  static String DatasBesidesLotterys ="/getDatasBesidesLotterys"+BASE_END;//获取体育，电子，真人分类的种类信息
     public  final  static String lottert_png =APP_URL_HEAD+"/mobile/v3/images/lottery/";//获取体育，电子，真人分类的种类信息
     public  final  static String login ="/login"+BASE_END;//登录
     public  final  static String accountInfo ="/accountInfo"+BASE_END;//获取用户信息
     public  final  static String reg_guest ="/reg_guest"+BASE_END;//获取免费账号
     public  final  static String meminfo ="/meminfo"+BASE_END;//余额
     public  final  static String logout ="/logout"+BASE_END;//登出
     public  final  static String register ="/register"+BASE_END;//注册
     public  final  static String getUniversalSwitch ="/getUniversalSwitch"+BASE_END;//首页的签到转盘开关
     public  final  static String regVerifycode ="/regVerifycode"+BASE_END;//验  证  码
    public  final  static  String login_code=APP_URL_HEAD+"/mobile/authnum.do";//获取登录的验证码
     public  final  static String regconfig ="/regconfig"+BASE_END;//2.	获取注册界面配置信息
     public  final  static String getVip =APP_URL_HEAD+"/center/member/meminfo/mobieLevel.do";//2.	vip
     public  final  static String updateuserInfo ="/update_sysaccount"+BASE_END;//更新用户内容
     public  final  static String exchangeConfig ="/exchangeConfig"+BASE_END;//兑换配置
     public  final  static String exchange ="/exchange"+BASE_END;//兑换交换
     public  final  static String getMsgCount ="/getMsgCount"+BASE_END;//获取站内没读信息昨天
     public  final  static String message_list =APP_URL_HEAD+"/center/news/message/list.do";//获取站内信
     public  final  static String withDrawData ="/withDrawData"+BASE_END;//获取提款信息
     public  final  static String drawcommit ="/center/banktrans/draw/drawcommit.do" ;//申请提款
     public  final  static String geRecordSwitch ="/geRecordSwitch"+BASE_END;//用户中心的几状态
     public  final  static String read =APP_URL_HEAD+"/center/news/message/batchRead.do";//设置站内信已读
     public  final  static String del_msg =APP_URL_HEAD+"/center/news/message/batchDelete.do";//删除内信
     public  final  static String up_login_pwd =APP_URL_HEAD+"/center/member/meminfo/newpwd.do";//更新登录密码
     public  final  static String drawing_money_pwd =APP_URL_HEAD+"/center/member/meminfo/repwd.do";//设置取款密码
     public  final  static String bindingBlank =APP_URL_HEAD+"/center/banktrans/draw/cmitbkinfo.do";//绑定银行卡
     public  final  static String chogzhijilu =APP_URL_HEAD+"/center/record/hisrecord/comrd.do";//充值记录
     public  final  static String qukjilu =APP_URL_HEAD+"/center/record/hisrecord/drawrd.do";//取款记录
     public  final  static String thirdlotteryrecord =APP_URL_HEAD+"/center/record/betrecord/thirdlotteryrecord.do";//三方彩票记录
     public  final  static String liveBetRecord =APP_URL_HEAD+"/mobile/v3/third/liveBetRecord.do";//真人投注记录
     public  final  static String chessrecord =APP_URL_HEAD+"/center/record/betrecord/chessrecord.do";//棋牌游戏记录
     public  final  static String egamerecord =APP_URL_HEAD+"/center/record/betrecord/egamerecord.do";//电子投注记录
     public  final  static String getRealNameType =APP_URL_HEAD+"/native/getRealNameType.do";//真人额度转换
     public  final  static String realpng =APP_URL_HEAD+"/mobile/v3/images/real/";//真人额度转换
     public  final  static String thirdRealTransMoney =APP_URL_HEAD+"/rc4m/thirdRealTransMoney.do";//转出转入第三方额度
     public  final  static String getBalanceitem =APP_URL_HEAD+"/rc4m/getBalance.do";//获得某真人余额


    /**
     * 投注记录
     */
    //public  final  static String getLotteryList ="/getLotteryList"+BASE_END;//获取彩票投注的彩票
    public  final  static String getMnyrecordType ="/getMnyrecordType"+BASE_END;//获取账变记录的类型
    public  final  static String get_lottery_order =APP_URL_HEAD+"/mobile/v3/get_lottery_order.do";// 	彩票投注记录查询
    public  final  static String cancelOrder =APP_URL_HEAD+"/mobile/v3/cancelOrder.do";// 	取消彩票订单
    public  final  static String betrecord =APP_URL_HEAD+"/center/record/betrecord/sportsrecord.do";// 体育投注记录 （沙巴体育）
    public  final  static String hg_sport =APP_URL_HEAD+"/mobile/v3/sport/hg/getOrderData.do";// 1)	体育投注记录 （皇冠体育
    public  final  static String mnyrecord_list=APP_URL_HEAD+"/center/record/mnyrecord/list.do";// 账变记录




     /**
      * 特殊请求
      * http://xbzhanshi.com/mobile/v3/lotterys.do?lotCode=LHC&version=2&needLast=true
      * 获取某一彩票的下一期时间和最新一期结果
      */
     public final static  String ServiceTime1 =APP_URL_HEAD+"/mobile/v3/lotterys.do?lotCode=";
     public final static  String ServiceTime2 ="&version=2&needLast=true";

     /**
      * 获取最新最新的所以彩票的结果，用于开奖公告
      */
     public final static  String OpenPrize =APP_URL_HEAD+"/mobile/v3/draw_notice_data.do";
     /**
      * 获取彩票结果列表
      * http://xbzhanshi.com/mobile/v3/draw_notice_details_data.do?lotCode=PCEGG&page=2&rows=10
      */
     public final static  String OpenPrizeResultList =APP_URL_HEAD+"/mobile/v3/draw_notice_details_data.do";
}
