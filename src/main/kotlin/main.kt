import com.mongodb.client.model.Filters
import org.bson.types.ObjectId
import java.util.Scanner
import org.litote.kmongo.eq

fun main() {
    val sc = Scanner(System.`in`)
    mainMenu(sc)
}

fun mainMenu(sc: Scanner) {
    while (true) {
        println(
            """What you want to do?
        | 1 -> Places
        | 2 -> Users
        | 0 -> END
    """.trimMargin()
        )
        when (sc.nextInt()) {
            1 -> placesMenu(sc)
            2 -> usersMenu(sc)
            0 -> break
        }
    }
}

fun placesMenu(sc: Scanner){
    while (true) {
        println(
            """What do you want to do?
        | 1 -> read places
        | 2 -> delete place
        | 0 -> go back
    """.trimMargin()
        )
        when (sc.nextInt()) {
            1 -> {
                AdminApp.getPlaceDataCollection().find().toList().forEach { println("-----> "+it) }
            }
            2 -> {
                println("Enter place id")
                val idToDelete = sc.next()
                AdminApp.getPlaceDataCollection().deleteOne(Filters.eq("_id", ObjectId(idToDelete)))
            }
            0 -> break
        }
    }
}

fun usersMenu(sc: Scanner){
    while (true) {
        println(
            """What do you want to do?
        | 1 -> read users
        | 2 -> delete user
        | 0 -> go back
    """.trimMargin()
        )
        when (sc.nextInt()) {
            1 -> {
                AdminApp.getUserDataCollection().find().toList().forEach { println(it) }
            }
            2 -> {
                println("Enter user id")
                val idToDelete = sc.next()
                AdminApp.getUserDataCollection().deleteOne(Filters.eq("_id", ObjectId(idToDelete)))
            }
            0 -> break
        }
    }
}