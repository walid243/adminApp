import org.bson.types.ObjectId

data class UserData(
    var _id: String= "" ,
    var name: String= "",
    var email: String= "",
    var avatar: ByteArray? = null
)