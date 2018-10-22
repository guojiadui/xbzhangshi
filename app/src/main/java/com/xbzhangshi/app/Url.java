package com.xbzhangshi.app;

public class Url {
    /**
     * 外部存储路径的文件名称
     */
    public  static  final String StoragePath = "xbzhangshi";

    public  final  static String APP_URL_HEAD ="http://xbzhanshi.com";
   public  final  static String intercept_Home_Back1 =APP_URL_HEAD+"/mobile";//返回webview的返回首页拦截
   public  final  static String intercept_Home_Back2 =APP_URL_HEAD+"/index.do";//返回webview的返回首页拦截釦

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
     public  final  static String withDrawData ="/pick_money_data"+BASE_END;//获取提款信息
     public  final  static String drawcommit ="/post_pick_money.do" ;//申请提款
     public  final  static String geRecordSwitch ="/geRecordSwitch"+BASE_END;//用户中心的几状态
     public  final  static String read =APP_URL_HEAD+"/center/news/message/batchRead.do";//设置站内信已读
     public  final  static String del_msg =BASE_URL+"/batchDelete.do";//删除内信
     public  final  static String up_login_pwd =APP_URL_HEAD+"/center/member/meminfo/newpwd.do";//更新登录密码
     public  final  static String drawing_money_pwd =BASE_URL+"/set_receipt_pwd.do";//设置取款密码
     public  final  static String bindingBlank =BASE_URL+"/post_bank_data.do";//绑定银行卡
     public  final  static String chogzhijilu =BASE_URL+"/comrd.do";//充值记录
     public  final  static String qukjilu =BASE_URL+"/drawrd.do";//取款记录
     public  final  static String thirdlotteryrecord =BASE_URL+"/thirdlotteryrecord.do";//三方彩票记录
     public  final  static String liveBetRecord =BASE_URL+"/liveBetRecord.do";//真人投注记录
     public  final  static String chessrecord =BASE_URL+"/chessrecord.do";//棋牌游戏记录
     public  final  static String egamerecord =BASE_URL+"/egamerecord.do";//电子投注记录
     public  final  static String getRealNameType =BASE_URL+"/getRealGameData.do";//真人额度转换
     public  final  static String realpng =APP_URL_HEAD+"/mobile/v3/images/real/";//真人额度转换
     public  final  static String thirdRealTransMoney =BASE_URL+"/thirdRealTransMoney.do";//转出转入第三方额度
     public  final  static String getBalanceitem =BASE_URL+"/getBalance.do";//获得某真人余额
     public  final  static String signByMonth =APP_URL_HEAD+"/sign/signByMonth.do";//获取每个月的签到天
     public  final  static String signIn =APP_URL_HEAD+"/sign/signIn.do";//签到
     public  final  static String lastrd =APP_URL_HEAD+"/center/active/lastrd.do";//获奖名单
     public  final  static String award =APP_URL_HEAD+"/center/active/award.do";//抽奖
     public  final  static String touxiang =APP_URL_HEAD+"/mobile/v3/images/touxiang.png";//头像
     public  final  static String active_infos =APP_URL_HEAD+"/native/active_infos.do";//获取优惠活动列表
     public  final  static String activeDesc =APP_URL_HEAD+"/mobile/v3/activeDesc.do";//活动详情
     public  final  static String redPackage =APP_URL_HEAD+"/mobile/redPackage.do";//红包页链接
     public  final  static String grab =APP_URL_HEAD+"/center/redpacket/grab.do";//绑定cookie的红包链接
     public  final  static String customerService =APP_URL_HEAD+"/mobile/v3/customerService.do";//客服
     public  final  static String helplist =APP_URL_HEAD+"/native/helplist.do";//帮助中心
     public  final  static String bet_lotterys =APP_URL_HEAD+"/mobile/v3/bet_lotterys.do?lotCode=";//投注网页
     public  final  static String Crown_Sports=APP_URL_HEAD+"/mobile/v3/sport/hg/goPage.do?dataType=TD_FT_MN";//皇冠体育
     public  final  static String Crown_Sports_cookis=APP_URL_HEAD+"/mobile/sports/hg/getData.do";//皇冠体育的cookie绑定
     public  final  static String turnlate=APP_URL_HEAD+"/mobile/v3/turnlate.do";//转盘页面
     public  final  static String turnlate_cookie=APP_URL_HEAD+"/center/active/lastrd.do";//转盘页面cookie的绑定
     public  final  static String getArticle=APP_URL_HEAD+"/getConfig/getArticle.do";//首页弹出框
     public  final  static String Version=BASE_URL+"/getCurrentLastVersion.do";//版本更新
     public  final  static String saveAppUpdate=BASE_URL+"/saveAppUpdate.do";//更新服务器的更新内容


    /**
     * 三方游戏的跳转
     */
    public  final  static String ele_index=APP_URL_HEAD+"/mobile/v3/third/index.do?code=";//电子游戏的跳转
    public  final  static String forwardReal =APP_URL_HEAD+"/native/forwardReal.do?playCode=";//真人游戏的跳转


 /**
  * webview
  */
 public  final  static String pay_select_way =APP_URL_HEAD+"/mobile/v3/pay_select_way.do";//充值的页
 public  final  static String generatePayOrder =APP_URL_HEAD+"/m/ajax/generatePayOrder.do";//用于充值页绑定cookie的链接
 public  final  static String pay =APP_URL_HEAD+"/onlinepay/pay.do";//用于充值页绑定cookie的链接

 /**
     * 投注记录
     */
    //public  final  static String getLotteryList ="/getLotteryList"+BASE_END;//获取彩票投注的彩票
    public  final  static String getMnyrecordType ="/getMnyrecordType"+BASE_END;//获取账变记录的类型
    public  final  static String get_lottery_order =BASE_URL+"/get_lottery_order.do";// 	彩票投注记录查询
    public  final  static String cancelOrder =APP_URL_HEAD+"/mobile/v3/cancelOrder.do";// 	取消彩票订单
    public  final  static String betrecord =BASE_URL+"/sportsrecord.do";// 体育投注记录 （沙巴体育）
    public  final  static String hg_sport =BASE_URL+"/getOrderData.do";// 1)	体育投注记录 （皇冠体育
    public  final  static String mnyrecord_list=BASE_URL+"/mnyrecordlist.do";// 账变记录




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
