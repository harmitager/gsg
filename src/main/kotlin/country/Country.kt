package country

import com.fasterxml.jackson.annotation.JsonBackReference
import province.Province

/**
 * Created by Harmitage on 16.08.2016.
 */
class Country(var name: String, val id: Int) {
    @JsonBackReference
    var provinces: MutableSet<Province>? = mutableSetOf()
    var population = 0
    var isAtWar = false
    var income = 0
    var money = 0

    init {
        for (province in provinces!!)
            province.owner = this
    }
}