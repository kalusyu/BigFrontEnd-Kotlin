package com.kalusyu.patterns.behavior

/**
 * desc:表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素的类的前提下定义作用于这些元素的新操作。
 *
 * @author biaowen.yu
 * @date 2020/7/20 19:46
 *
 **/
interface ReportVisitable {
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(val costPerYear: Long) : ReportVisitable {
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}

class TimeAndMaterialsContract(val costPerHour: Long, val hours: Long) : ReportVisitable {
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}

class SupportContract(val costPerMonth: Long) : ReportVisitable {
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}

interface ReportVisitor<out R> {
    fun visit(contract: FixedPriceContract): R
    fun visit(contract: TimeAndMaterialsContract): R
    fun visit(contract: SupportContract): R
}


class MonthlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract): Long = contract.costPerYear / 12

    override fun visit(contract: TimeAndMaterialsContract) = contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract) = contract.costPerMonth
}

class YearlyCostReportVisitor : ReportVisitor<Long> {
    override fun visit(contract: FixedPriceContract) = contract.costPerYear

    override fun visit(contract: TimeAndMaterialsContract) =
        contract.costPerHour * contract.hours

    override fun visit(contract: SupportContract): Long = contract.costPerMonth * 12
}