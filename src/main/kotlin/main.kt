import country.Country
import province.Province

/**
 * Created by harmitage on 16.08.2016.
 */
fun main(args: Array<String>) {
    val d = Province("prov1",5,1,10,null,null,null,null,10,100)
    val e = Province("prov2",6,2,25,null,null,null,null,10,100)
    val f = Province("prov3",7,3,20,null,null,null,null,10,100)
    val g = Province("prov4",8,4,30,null,null,null,null,10,100)
    var c = Country("test",1, mutableSetOf(d,e,f))
    println(c.income)
    g.owner = c
    println(c.income)
    d.controller = c
    e.controller = c
    f.controller = c
    g.controller = c
    println(c.income)
    g.income = 50
    println(c.income)
}