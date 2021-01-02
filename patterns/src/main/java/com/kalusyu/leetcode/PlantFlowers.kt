package com.kalusyu.leetcode

import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2021/1/1 15:18
 *
 **/
class PlantFlowers {

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
        mutableList.forEach{
            val count = map.getOrDefault(it,0)
            for (i in 1..count){
                sortedNum.add(it)
            }
        }
        return sortedNum.toIntArray()
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


    plantFlowers.frequencySort(arrayOf(1,1,2,2,2,3).toIntArray())
}

