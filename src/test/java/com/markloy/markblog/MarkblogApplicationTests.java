package com.markloy.markblog;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class MarkblogApplicationTests {

    @Test
    void contextLoads() {

        String title = "linux  spring   命令 4";
        String replace = title.replace(" ", "|");
        System.out.println(replace);
        String[] split = replace.split("|");
        System.out.println(Arrays.toString(split));

        ArrayList<String> list = new ArrayList<>();
        for (String s : split) {
            if (list.isEmpty()) {
                list.add(s);
            } else {
                int size = list.size();
                if ( list.get( size - 1).equals("|")) {
                    if (!"|".equals(s)) {
                        list.add(s);
                    }
                } else {
                    if (!"|".equals(s)) {
                        list.add(s);
                    } else {
                        list.add("|");
                    }
                }
            }
        }
        System.out.println(Arrays.toString(list.toArray()));
        StringBuilder search = new StringBuilder();
        for (String item : list) {
            search.append(item);
        }
        System.out.println(search.toString());
    }

}
