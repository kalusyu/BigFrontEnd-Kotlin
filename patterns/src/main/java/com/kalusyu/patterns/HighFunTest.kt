package com.kalusyu.patterns


/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/3 11:56
 *
 **/

fun main() {
    val items = listOf(1, 2, 3, 4, 5)
    val p = items.fold(0, { acc: Int, i: Int ->
        print("acc = $acc, i = $i, ")
        val result = acc + i
        print("result = $result")
        result
    })
    println("p = $p")

    val funType = get {
        "$it Hello"
    }

    println("funType = $funType")

    val repeat = noParam {
        "StringString"
    }

    println("repeat = $repeat")

    textTest{
        name = "lkl"
        age = 10
    }
    textTest2{
        it.name = "22323"
        it.age = 23
    }

}

fun get(a: (Int) -> String): String {
    return a(10)
}

fun noParam(a: () -> String): String {
    return a()
}

fun textTest(attributes: Text.() -> Unit) {
//    Text().apply { attributes() }
    attributes(Text())
}

fun textTest2(attributes: (Text) -> Unit) {
//    Text().apply { attributes() }
    attributes(Text())
}

class Text {
    var name: String? = null
    var age: Int? = null
}