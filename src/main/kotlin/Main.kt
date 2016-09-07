import world.World

/**
 * Created by Harmitage on 07.09.2016.
 */
fun main(args: Array<String>) {
    for (i in 1..1) {
        World.next(null)
        print(World.send())
    }
}