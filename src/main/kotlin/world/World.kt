package world

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
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
    //TODO: make this a companion object
    var countries: MutableSet<Country> = Reader.JSON.readValue(File("resources/countries.init"))
    val provinces: Set<Province> = Reader.JSON.readValue(File("resources/provinces.init"))
    val provincesByID: MutableMap<Int, Province> = mutableMapOf()
    var countriesByID: MutableMap<Int, Country> = mutableMapOf()

    init {
        for (province in provinces)
            provincesByID.put(province.id, province)
        for (country in countries)
            countriesByID.put(country.id, country)
        var ownership: MutableMap<String, Int> = Reader.JSON.readValue(File("resources/ownership.init"))
        var control: MutableMap<String, Int> = Reader.JSON.readValue(File("resources/control.init"))
        var neighbourhood: MutableMap<String, ArrayNode> = Reader.JSON.readValue(File("resources/neighbourhood.init"))
        for (x in control.keys)
            provincesByID[x.toInt()]?.controller = countriesByID[ownership[x]]
        for (x in ownership.keys)
            provincesByID[x.toInt()]?.owner = countriesByID[ownership[x]]
        for (x in neighbourhood) {
            for (y in x.value) {
                provincesByID[x.key.toInt()]?.neighbours!!.add(provincesByID[y.asInt()]!!)
            }
        }
        print(provincesByID)
    }

    fun next(input: Input?) {
        if (input != null) {
            //TODO:Add player input to the inputs list
        }
        for (province in provinces) {
            province.populationChange = (province.population.toDouble() * (Math.random() - 0.5f)).toInt()
            province.income = (province.income.toDouble() * (Math.random() - 0.5f)).toInt()
        }
        for (country in countries) {
            country.money += country.income
        }
        //TODO:Process the world
    }

    fun send(): String {
        return Reader.JSON.writeValueAsString(this)
    }
}