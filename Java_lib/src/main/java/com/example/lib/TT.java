package com.example.lib;

/**
 * Created by guozhk on 2018/5/15.
 */

public class TT {
    public static void main(String[] agr) {

        String s="满30减5元";
        String str= s.substring(s.indexOf("5")+1,s.length());
        System.out.println(str);


//        System.out.println(finds("he lle"," ll"));
//
//        System.out.println("he lle".indexOf(" ll"));

    }


    private static int finds(String text, String p) {

        if (text == null) {
            return -1;
        }

        if (text.equals("")) {
            return -1;
        }

        if (p == null) {
            return -1;
        }
        if (p.equals("")) {
            return -1;
        }

        int l = text.length();
        int pl = p.length();
        if (l < pl) {
            return -1;
        }

        char fc = p.charAt(0);
        char ec = p.charAt(pl - 1);
        for (int i = 0; i < l - pl; i++) {
            char c = text.charAt(i);
            if (c == fc) {
                char e = text.charAt(i + pl - 1);
                if (e != ec) {
                    continue;
                }

                for (int k = i; k < i + pl; k++) {
                    if (text.charAt(k) != p.charAt(k - i)) {
                        break;
                    }
                }
                return i;
            }
        }


        return -1;
    }




}
