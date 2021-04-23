package com.aki.randomThreadNumber;


import java.util.concurrent.ThreadLocalRandom;

public class MMain {
    private static final String[] DICTIONARY = {"小葱拌豆腐，一穷二白", "只要功夫深，铁棒磨成针", "山中无老虎，猴子称霸王"};

    private static String nextQuote() {
        //线程安全岁基类，避免多线程环境发生错误
        int quote = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
        return DICTIONARY[quote];
    }

    public static void main(String[] args) {
        String s = nextQuote();
        System.out.println(s);
    }
}
