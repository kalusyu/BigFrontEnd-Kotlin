package com.kalusyu.leetcode

import java.lang.StringBuilder

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2021/1/4 9:20
 *
 **/

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 */
fun lengthOfLongestSubstring(s: String): Int {

    val dic = HashMap<Char, Int>()
    var i = -1
    var res = 0
    s.toCharArray().forEachIndexed { index, c ->

        // 出现重复的字符，更新start的位置
        if (dic.containsKey(c)) {
            i = Math.max(i, dic[c]!!); // 更新左指针 i
            print("i = $i")
        }
        // 最新的字符的最新的位置
        dic.put(c, index); // 哈希表记录
        res = Math.max(res, index - i); // 更新结果
        print("index = $index, res = $res")
        println()
    }
    return res;


}

fun main() {
    println(lengthOfLongestSubstring("abcccdacefg"))
}