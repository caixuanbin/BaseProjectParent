package com.xbcai.function.list;

import java.util.Arrays;
import java.util.List;

public class ListUtils {
    /**
     * 改变list集合里面的对象信息
     */
    public static void changeList(){
        List<Student> lists = Arrays.asList(new Student("A",1), new Student("B",2), new Student("C",3));
        lists.forEach(item-> item.setAge(item.getAge()+11));
        lists.forEach(item-> System.out.println(item.toString()));
    }

    public static void main(String[] args) {
        changeList();
    }
}
