package province

import country.Country
import culture.Culture
import faith.Faith
import world.World
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty

/**
 * Created by harmitage on 16.08.2016.
  */
class Province(val name: String, var population: Int,_income:Int, val id: Int, _owner: Country?, _controller: Country?, var faith: Faith?, var culture: Culture?, var militancy: Int,var populationLimit:Int) {

    var populationChange by Delegates.observable(0, {
      prop, old, new ->
        if (population+new<0) {
            this.owner?.population = this.owner?.population?.minus(population)!!
            population = 0
        } else if(population+new>populationLimit){
            this.owner?.population = this.owner?.population?.plus(populationLimit-population)!!
            population = populationLimit
        } else {
            this.owner?.population = this.owner?.population?.plus(new)!!
            population += new
        }
    })

    var income by Delegates.observable(_income,{
        prop,old,new->
            this.owner?.income = this.owner?.income?.plus(new-old)!!
    })

    var owner: Country? by Delegates.observable(_owner, {
        prop,old,new->
        old?.provinces?.remove(this)
        old?.population = old?.population?.minus(this.population)!!
        new?.provinces?.add(this)
        new?.population = new?.population?.plus(this.population)!!
        old?.income = old?.income?.minus(this.income)!!
        if (this.controller==new)
            new?.income = new?.income?.plus(this.income)!!
    })

    var controller:Country? by Delegates.observable(_controller,{
        prop,old,new->
        if (this.owner==new)
            new?.income = new?.income?.plus(this.income)!!
    })


}