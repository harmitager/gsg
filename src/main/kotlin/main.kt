import country.Country
import province.Province
import world.World

/**
 * Created by harmitage on 16.08.2016.
 */
fun main(args: Array<String>) {
    for(province in World.provinces) {
        println(province.name)
        println(province.income)
    }
    for (country in World.countries){
        println(country.income)
    }
}