package world

import input.Input
import province.Province
import reader.Reader
import java.io.File

/**
 * Created by Harmitage on 19.08.2016.
 */

object World {
    val provinces: Set<Province> = Reader.JSON.readValue(File("resources/provinces.init"))

    //TODO: find a way to import Countries using either provinceID or crossreferencing
    //var countries:MutableSet<Country> = Reader.JSON.readValue(File("resources/countries.init"))
    //var countriesID: HashMap<Int,Country> = hashMapOf()
    init {
        // for (country in countries)
        //   countriesID.plus(Pair(country.id,country))
    }

    fun next(input: Input?) {
        if (input != null) {
            //TODO:Add player input to the inputs list
        }
        //TODO:Process the world
    }
}