import org.bson.types.ObjectId

data class PlaceData(
    var _id: ObjectId? = null ,
    var name: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var ownerId: String = "",
    var description: String? = null,
    var image: ByteArray? = null
)