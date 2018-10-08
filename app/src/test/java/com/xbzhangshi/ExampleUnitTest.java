package com.xbzhangshi;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.xbzhangshi.mvp.home.bean.BalanceBean;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        try {
            BalanceBean t = JSON.parseObject("{}", BalanceBean.class);
            if (t != null) {
                System.out.print("--"+t.toString());
            } else {
                System.out.print("null");
            }
        } catch (Exception e) {
           System.out.print("catch");
        }
    }
}