package com.kalusyu.leetcode

import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.max

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2021/1/1 15:18
 *
 **/
class PlantFlowers {

    /**
     * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
    给你一个整数数组  flowerbed 表示花坛，由若干 0 和 1 组成，其中 0 表示没种植花，1 表示种植了花。另有一个数 n ，
    能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false。
     */
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        if (n <= 0) return true
        var number = n
        // 考虑边界什么时候不能种花
        flowerbed.forEachIndexed { index, i ->
            if (i == 1) return@forEachIndexed

            if (index > 0 && flowerbed[index - 1] == 1) return@forEachIndexed

            if (index < flowerbed.size - 1 && flowerbed[index + 1] == 1) return@forEachIndexed

            flowerbed[index] = 1
            number--

        }
        return number <= 0
    }

    /**
     * 异字母
     * 将两个字符串排序，比较两个字符串是否相等，注意边界条件，特别是刚输入的边界条件
     */
    fun isAnagram(s: String, t: String): Boolean {

        if (s.length != t.length) return false
        val sArray = s.toCharArray()
        val tArray = t.toCharArray()
        Arrays.sort(sArray)
        Arrays.sort(tArray)

        return sArray.contentEquals(tArray)

    }

    /**
     * 离散方式
     */
    fun isAnagram2(s: String, t: String): Boolean {

        if (s.length != t.length) return false
        val intArrays = IntArray(26)
        s.toCharArray().forEach {
            intArrays[it - 'a']++
        }
        t.toCharArray().forEach {
            intArrays[it - 'a']--
            // 等于0 说明出现一次，大于0说明出现了大于1次，小于0说明在s中没有出现次数
            if (intArrays[it - 'a'] < 0) return false
        }

        return true
    }

    /**
     * 两个数组的交集
     */
    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        if (nums1.size > nums2.size) {
            return intersection(nums2, nums1)
        }

        val map = HashMap<Int, Int>()
        nums1.forEach {
            val count = map.getOrDefault(it, 0) + 1
            map[it] = count
        }

        val inter = arrayListOf<Int>()
        nums2.forEach {
            var count = map.getOrDefault(it, 0)
            if (count > 0) {
                inter.add(it)
                count--
                if (count > 0) {
                    map[it] = count
                } else {
                    map.remove(it)
                }
            }
        }
        return inter.toIntArray()

    }


    /**
     * 上升下降字符串
     */
    fun sortString(s: String): String {
        val intArr = IntArray(26)
        s.toCharArray().forEach {
            intArr[it - 'a']++
        }
        val buffer = StringBuffer()
        while (buffer.length < s.length) {
            intArr.forEachIndexed { index, it ->
                if (it != 0) {
                    buffer.append('a' + index)
                    intArr[index]--
                }
            }

            for (i in 25 downTo 0 step 1) {
                val number = intArr[i]
                if (number != 0) {
                    buffer.append('a' + i)
                    intArr[i]--
                }
            }
        }
        return buffer.toString()
    }

    /**
     * Given a string s and an integer array indices of the same length.
    The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
    Return the shuffled string.
    给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
    请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
    返回重新排列后的字符串。
     */
    fun restoreString(s: String, indices: IntArray): String {
        val strArray = CharArray(s.length)
        s.toCharArray().forEachIndexed { index, c ->
            val position = indices[index]
            strArray[position] = c
        }
        return String(strArray)
    }

    /**
     * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。
    请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
    考察排序方式
     */
    fun average(salary: IntArray): Double {
        var maxvalue = Int.MIN_VALUE
        var minValue = Int.MAX_VALUE
        var sum = 0
        salary.forEach {
            maxvalue = maxOf(maxvalue, it)
            minValue = minOf(minValue, it)
            sum += it
        }
        return (sum - maxvalue - minValue).toDouble() / (salary.size - 2)
    }

    /**
     * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。
     * 请你以 任意顺序 连接 pieces 中的数组以形成 arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
     * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
     */
    fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
        val map = HashMap<Int, IntArray>()
        pieces.forEach {
            map[it[0]] = it
        }

        var i = 0
        while (i < arr.size) {
            val value = arr[i]
            if (map.containsKey(value)) {
                val intArr = map[value]
                intArr?.forEach {
                    if (arr[i] == it) {
                        i++
                    } else {
                        return false
                    }
                }
            } else {
                return false
            }
        }
        return true

    }

    /**
     * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，
     * 请你按照数值本身将它们 降序 排序。请你返回排序后的数组。
     */

    fun frequencySort(nums: IntArray): IntArray {
        // 计算该数组每个数值出现的频率， 哈希表
        // 对频率进行升序排序
        // 对频率相同的降序排序
        val map = HashMap<Int, Int>()
        nums.forEach {
            val count = map.getOrDefault(it, 0) + 1
            map[it] = count
        }

        val list = map.keys.toList()
        val comparator: Comparator<Int> = kotlin.Comparator { o1, o2 ->
            if (map[o1] == map[o2]) {
                o2 - o1
            } else {
                map[o1]!! - map[o2]!!
            }
        }
        val mutableList = list.sortedWith(comparator)// 3,1,2

        val sortedNum = arrayListOf<Int>()
        mutableList.forEach {
            val count = map.getOrDefault(it, 0)
            for (i in 1..count) {
                sortedNum.add(it)
            }
        }
        return sortedNum.toIntArray()
    }

    /**
     * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。
     * 如果不能形成任何面积不为零的三角形，返回 0。
     */
    fun largestPerimeter(A: IntArray): Int {
        // 判断三个数是否能组成三角形
        Arrays.sort(A)

        for (i in A.size - 1 downTo 2)
            if (A[i - 3] + A[i - 2] > A[i - 1]) {
                return A[i - 3] + A[i - 2] + A[i - 1]
            }
        return 0
    }

    /**
     * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
    对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
    你可以返回任何满足上述条件的数组作为答案。
     */
    fun sortArrayByParityII(A: IntArray): IntArray {
//        思路和算法
//        遍历一遍数组把所有的偶数放进 ans[0]，ans[2]，ans[4]，依次类推。
//        再遍历一遍数组把所有的奇数依次放进 ans[1]，ans[3]，ans[5]，依次类推。

        val sorted = IntArray(A.size)
        var i = 0
        A.forEach {
            if (it % 2 == 0) {
                sorted[i] = it
                i += 2
            }
        }

        i = 1
        A.forEach {
            if (it % 2 == 1) {
                sorted[i] = it
                i += 2
            }
        }
        return sorted
    }

    /**
     * 给你两个数组，arr1 和 arr2，
    arr2 中的元素各不相同
    arr2 中的每个元素都出现在 arr1 中
    对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
    示例：
    输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
    输出：[2,2,2,1,4,3,3,9,6,7,19]

     */
    fun relativeSortArray(arr1: IntArray, arr2: IntArray): IntArray {

        val map = HashMap<Int, Int>()
        arr2.forEachIndexed { index, it ->
            map[it] = index
        }

        val list = arr1.toList()
        val comparator: Comparator<Int> = kotlin.Comparator { o1, o2 ->
            if (map.containsKey(o1) && map.containsKey(o2)) {
                map[o1]!! - map[o2]!!
            } else if (map.containsKey(o1)) {
                -1
            } else if (map.containsKey(o2)) {
                1
            } else {
                o1 - o2
            }
        }
        val mutableList = list.sortedWith(comparator)
        return mutableList.toIntArray()

    }


    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
    进阶：
    如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
    s = "aaaaaa" t = "bbaaaa"
     */
    fun isSubsequence(s: String, t: String): Boolean {
        /*if (s.length > t.length) return false

        var position = 0
        var count = 0
        s.toCharArray().forEach outter@{ out ->
            t.forEachIndexed { index, it ->
                if (out == it && index >= position) {
                    position = index + 1
                    count++
                    return@outter
                }
            }
        }
        if (count == s.length) {
            return true
        }
        return false*/
        // 贪心算法
        val sc = s.toCharArray()
        val tc = t.toCharArray()
        var i = 0
        var j = 0
        while (i < s.length && j < t.length) {
            if (sc[i] == tc[j]) {
                i++
            }
            j++
        }
        return i == s.length
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */
    fun maxProfit(prices: IntArray): Int {
        /*var sum = 0

        var maxValue = Int.MIN_VALUE
        prices.forEach {
            maxValue = maxOf(maxValue, it)
        }

        var buy = maxValue
        prices.forEach {
            // 现在的大于第二天的卖出
            // 现在的小于第二天买的买入
            if (it < buy) {
                buy = it
            } else {
                sum += it - buy
                buy = it
            }
        }

        return sum*/
        //官方
        var sum = 0
        prices.forEachIndexed { index, i ->
            if (index == 0) return@forEachIndexed
            sum += maxOf(0, prices[index] - prices[index - 1])
        }
        return sum
    }

    /**
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
    对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
    都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     */
    fun findContentChildren(g: IntArray, s: IntArray): Int {
        // 对二者进行排序
        Arrays.sort(g)
        Arrays.sort(s)
        var i = 0
        var j = 0
        while (i < g.size && j < s.size) {
            if (s[j] >= g[i]) {
                i++
            }
            j++
        }
        return i
    }

    /**
     * 有一堆石头，每块石头的重量都是正整数。
    每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
    如果 x == y，那么两块石头都会被完全粉碎；
    如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
    最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     */
    fun lastStoneWeight(stones: IntArray): Int {
        // o1 - o2 降序
        // o2 - o1 升序
        val comparator: Comparator<Int> = Comparator { o1, o2 ->
            o2 - o1
        }
        val pq: PriorityQueue<Int> = PriorityQueue(comparator)
        stones.forEach {
            pq.offer(it)
        }

        while (pq.size > 1) {
            val a = pq.poll()
            val b = pq.poll()
            if (a > b) {
                pq.offer(a - b)
            }
        }
        return if(pq.isEmpty()) 0 else pq.poll()
    }


}


fun main() {
    val plantFlowers = PlantFlowers()
//    val flowerbed = arrayOf(1, 0, 0, 0, 0, 0, 1)
    val flowerbed = arrayOf(1, 0)

    println(plantFlowers.canPlaceFlowers(flowerbed.toIntArray(), 0))

    println(plantFlowers.isAnagram("abc", "cba"))

    val array = plantFlowers.intersection(
        arrayOf(1, 10, 2, 33, 2).toIntArray(),
        arrayOf(2, 3, 9, 10).toIntArray()
    )
    array.forEach {
        print(",$it")
    }

    println()
    val sort = plantFlowers.sortString("aabbcdddc")
    println("$sort")

    val arrays = HashSet<Int>()


    plantFlowers.frequencySort(arrayOf(1, 1, 2, 2, 2, 3).toIntArray())

    plantFlowers.sortArrayByParityII(arrayOf(4, 2, 5, 7).toIntArray())

    plantFlowers.isSubsequence("aaaaaa", "bbaaaa")
}

