package country

import province.Province

/**
 * Created by Harmitage on 16.08.2016.
 */
class Country(var name: String, val id: Int) {
    var provinces: MutableSet<Province>? = mutableSetOf()
    var population = 0
    var isAtWar = false
    var income = 0

    init {
        for (province in provinces!!)
            province.owner = this
    }
}