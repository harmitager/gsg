package world

import com.fasterxml.jackson.module.kotlin.readValue
import country.Country
import input.Input
import province.Province
import reader.Reader
import java.io.File

/**
 * Created by Harmitage on 19.08.2016.
 */

object World {
    var countries: MutableSet<Country> = Reader.JSON.readValue(File("resources/countries.init"))
    val provinces: Set<Province> = Reader.JSON.readValue(File("resources/provinces.init"))
    val provincesByID: MutableMap<Int,Province> = mutableMapOf()
    var countriesByID: MutableMap<Int,Country> = mutableMapOf()

    init {
        for (province in provinces)
            provincesByID.put(province.id,province)
        for (country in countries)
            countriesByID.put(country.id,country)
        var ownership:MutableMap<String, Int> = Reader.JSON.readValue(File("resources/ownership.init"))
        var control:MutableMap<String,Int> = Reader.JSON.readValue(File("resources/control.init"))
        for (x in control.keys)
            provincesByID[x.toInt()]?.controller = countriesByID[ownership[x]]
        for (x in ownership.keys)
            provincesByID[x.toInt()]?.owner = countriesByID[ownership[x]]
    }

    fun next(input: Input?) {
        if (input != null) {
            //TODO:Add player input to the inputs list
        }
        //TODO:Process the world
    }
}