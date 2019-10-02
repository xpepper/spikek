//@DslMarker
//annotation class CompanyDsl
//
//@CompanyDsl
//class CompanyBuilderDsl : Company.Builder()
//
//@CompanyDsl
//class EmployeeBuilderDsl : Employee.Builder()
//
//@CompanyDsl
//class OfficeBuilderDsl : Office.Builder()
//
//inline fun company(buildCompany: CompanyBuilderDsl.() -> Unit): Company {
//    val builder = CompanyBuilderDsl()
//    // Since `buildCompany` is an extension function for Company.Builder,
//    // buildCompany() is called on the Company.Builder object.
//    builder.buildCompany()
//    return builder.build()
//}
//
//inline fun CompanyBuilderDsl.employee(
//    buildEmployee: EmployeeBuilderDsl.() -> Unit
//) {
//    val builder = EmployeeBuilderDsl()
//    builder.buildEmployee()
//    addEmployee(builder)
//}
//
//inline fun CompanyBuilderDsl.office(
//    buildOffice: OfficeBuilderDsl.() -> Unit
//) {
//    val builder = OfficeBuilderDsl()
//    builder.buildOffice()
//    addOffice(builder)
//}