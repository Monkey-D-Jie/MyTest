package com.jf.mydemo.mytest.Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-04-15 16:55
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class CommonUtil {
    /**
     * 删除某一集合中的指定的某个元素
     *
     * @author: Wangjie
     * @date:   2019-03-26 20:09:06
     * @description: 在此暂时只支持基本数据类型，
     * 实体类别还未测试。用泛型来让其具有可扩展性
     * @param  list  待删除元素的集合
     * @param  emlement   指定的删除元素
     */
    public static  <T> void  deleteListSpecifyElement(List<T> list, T  emlement){
        Iterator<T> it = list.iterator();
        while(it.hasNext()){
            T x = it.next();
            if(x.equals(emlement)){
                it.remove();
            }
        }
    }

    /**
     * 对map作分组处理
     *
     * @author: Wangjie
     * @date:   2019-04-09 17:20:28
     * @description: 对各元素作分组处理
     * @param  list   待处理的集合
     * @return  List 处理好的结果
     */
    private List<TreeMap<String,Object>> groupMap(List<TreeMap<String,Object>> list, List<ExamReportJob> findList){
        //用java8的流式处理做分组
        List<TreeMap<String,Object>> result =new ArrayList<>();
        Map<String, List<TreeMap<String, Object>>> glist = list.stream().collect(Collectors.groupingBy(e -> e.get("day").toString()));
        glist.forEach((k,slist)->{
            TreeMap<String,Object> nmap=new TreeMap<>();
            IntSummaryStatistics sumcc = slist.stream().collect(Collectors.summarizingInt(e->Integer.valueOf(e.get("dayJobsStatus").toString())));
            nmap.put("day", slist.get(0).get("day"));
            //求和
            long statusSum = sumcc.getSum();
            Integer jobStatus = 0;
            if(statusSum == slist.size()){
                //说明当天的都是已完成的了
                jobStatus = 1;
            }else if(statusSum == slist.size()*2){
                //说明是处理的上课时间未到的数据
                jobStatus = 2;
            }
            nmap.put("dayJobsStatus",jobStatus);
            result.add(nmap);
        });
        return result;
    }
}
