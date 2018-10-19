package com.xbzhangshi.chat.chatroom;


import android.util.Log;

import com.xbzhangshi.app.Url;
import com.xbzhangshi.chat.listener.IConstants;
import com.xbzhangshi.chat.socket.AppSocket;
import com.xbzhangshi.single.UserInfo;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author silencezwm on 2017/8/25 上午11:27
 * @email silencezwm@gmail.com
 * @description 主聊天室
 */



public class MainChatRoom extends BaseChatRoom {

    private static volatile MainChatRoom INSTANCE = null;

    public static MainChatRoom getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException("must first call the init() method");
        }
        return INSTANCE;
    }

    public static void init() {
       // new MainChatRoom();
    }

    private MainChatRoom() {
        super();
        INSTANCE = this;
        initAppSocket();
    }

    /**
     * 初始化Socket
     */
    public void initAppSocket() {
      //   url = "ws://xbzhanshi.com/websocket?token="+UserInfo.getInstance().getLoginBean().getAccessToken();
      // String url = "http://"+Url.APP_URL_HEAD+"/chat/websocket?token=";
      /*  AppSocket.Builder builder = new AppSocket.Builder(url)
                .setEmitterListener(this);
        AppSocket.init(builder).connect();*/
  // final String url="ws://chat.xbxb555.com/websocket?token=yOHrt3g3x1SnaBFEBi31eRgloAtHhQpoTZP2Csxiz+yKQlesTh4F7UPOLC61H6twiRlM6zXSEdyEvruT0BQvPlrAu9UTHwns5wHXT1Dyk8WMgozBn/TcZrkqC/si2iFTflfRoeQ4MzSCzSNl3R9TFw==";
       final  String url ="ws://121.40.165.18:8800";
        Log.e("picher_log",url);
         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //TODO 这里URL 别忘了切换到自己的IP
                    WebSocketClient   mSocketClient = new WebSocketClient(new URI(url), new Draft_10()) {
                        @Override
                        public void onOpen(ServerHandshake handshakedata) {
                            Log.d("picher_log", "打开通道" + handshakedata.getHttpStatus());
                            //  handler.obtainMessage(0, message).sendToTarget();
                        }

                        @Override
                        public void onMessage(String message) {
                            Log.d("picher_log", "接收消息" + message);
                            //  handler.obtainMessage(0, message).sendToTarget();
                        }

                        @Override
                        public void onClose(int code, String reason, boolean remote) {
                            Log.d("picher_log", "通道关闭"+reason+":"+code);
                            // handler.obtainMessage(0, message).sendToTarget();
                        }

                        @Override
                        public void onError(Exception ex) {
                            Log.d("picher_log", "链接错误");
                        }
                    };
                    mSocketClient.connect();

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
