package com.kalusyu.patterns.struct

/**
 * desc:为子系统中的一组接口提供一个一致的界面。Facade模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 *
 * @author biaowen.yu
 * @date 2020/7/9 9:14
 *
 **/
class Company {
    var name: String = ""
    var companyId: String = ""
    var companyAccount: String = ""
    var companyTax: String = ""
}

interface AdminOfIndustry {
    fun register(name: String): Company
}

interface Bank {
    fun openAccount(companyId: String): String
}

interface Taxation {
    fun applyTaxCode(companyId: String): String
}


interface Facade {
    // 比如现在有很多的web子系统，但是对外会提供一个restful接口供其他开发者使用，其他开发者不必关系到底与哪个子系统交互，统一由这个外观管理者处理
    fun openCompany(name: String): Company

}

class FacadeImpl() : Facade {

    override fun openCompany(name: String): Company {
        val adminOfIndustry: AdminOfIndustry = AdminOfIndustryImpl()
        val bank: Bank = BankImpl()
        val taxation: Taxation = TaxationImpl()
        val company = adminOfIndustry.register(name)
        company.companyAccount = bank.openAccount(companyId = company.companyId)
        company.companyTax = taxation.applyTaxCode(company.companyId)
        return company
    }
}

class AdminOfIndustryImpl : AdminOfIndustry {
    override fun register(name: String): Company {
        println("Company register")
        val company = Company()
        company.name = name
        company.companyId = "123"
        return company
    }
}

class BankImpl : Bank {
    override fun openAccount(companyId: String) = "1234567890"
}

class TaxationImpl : Taxation {
    override fun applyTaxCode(companyId: String): String {
        return "10000"
    }
}