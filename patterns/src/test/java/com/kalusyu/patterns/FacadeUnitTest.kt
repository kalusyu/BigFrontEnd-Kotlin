package com.kalusyu.patterns

import com.kalusyu.patterns.struct.FacadeImpl
import org.junit.jupiter.api.Test

/**
 * desc:
 *
 * @author biaowen.yu
 * @date 2020/7/14 9:56
 *
 **/
class FacadeUnitTest {
    @Test
    fun testFacade() {
        val facade = FacadeImpl()
        val company = facade.openCompany("kalusyu")
        println("company:name=${company.name},id=${company.companyId},account=${company.companyAccount},tax=${company.companyTax}")
    }
}