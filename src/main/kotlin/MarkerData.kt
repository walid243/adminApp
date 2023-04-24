import org.bson.types.ObjectId

data class MarkerData(
    val _id: ObjectId,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val ownerId: String
)