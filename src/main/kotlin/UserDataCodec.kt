import org.bson.BsonBinary
import org.bson.BsonReader
import org.bson.BsonType
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

class UserDataCodec : Codec<UserData> {
    override fun encode(writer: BsonWriter?, value: UserData?, encoderContext: EncoderContext?) {
        writer?.apply {
            writeStartDocument()
            writeString("_id", value?._id)
            writeString("name", value?.name)
            writeString("email", value?.email)
            if (value?.avatar != null) {
                writeBinaryData("avatar", value.avatar?.let { BsonBinary(it) })
            } else {
                writeNull("avatar")
            }
            writeEndDocument()
        }
    }

    override fun getEncoderClass(): Class<UserData> {
        return UserData::class.java
    }

    override fun decode(reader: BsonReader?, decoderContext: DecoderContext?): UserData {
        reader?.apply {
            readStartDocument()
            var id = ""
            var name = ""
            var email = ""
            var avatar: ByteArray? = null

            while (readBsonType() != BsonType.END_OF_DOCUMENT) {
                when (readName()) {
                    "_id" -> id = readString()
                    "name" -> name = readString()
                    "email" -> email = readString()
                    "avatar" -> avatar = if (currentBsonType == BsonType.BINARY) readBinaryData().data else {
                        skipValue(); null
                    }
                }
            }
            readEndDocument()
            return UserData(id, name, email, avatar)
        }
        return UserData()
    }

}