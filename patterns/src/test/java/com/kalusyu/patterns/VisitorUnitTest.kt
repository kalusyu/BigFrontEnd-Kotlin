package com.kalusyu.patterns

import com.kalusyu.patterns.behavior.*
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/21 10:40
 *
 **/
class VisitorUnitTest {

    @Test
    fun testVisitor() {
        val projectAlpha = FixedPriceContract(costPerYear = 10000)
        val projectBeta = SupportContract(costPerMonth = 500)
        val projectGama = TimeAndMaterialsContract(costPerHour = 150, hours = 50)
        val projectKappa = TimeAndMaterialsContract(costPerHour = 50, hours = 50)
        val projects = arrayOf(projectAlpha, projectBeta, projectGama, projectKappa)

        val monthlyCostReportVisitor = MonthlyCostReportVisitor()
        val monthlyCost = projects.map { it.accept(monthlyCostReportVisitor) }.sum()

        println("monthlyCost = $monthlyCost")

        val yearlyCostReportVisitor = YearlyCostReportVisitor()
        val yearlyCost = projects.map {
            it.accept(yearlyCostReportVisitor)
        }.sum()

        println("yearlyCost = $yearlyCost")
    }
}