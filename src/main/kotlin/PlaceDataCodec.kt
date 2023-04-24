import org.bson.BsonBinary
import org.bson.BsonReader
import org.bson.BsonType
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import org.bson.types.ObjectId

class PlaceDataCodec: Codec<PlaceData> {
    override fun encode(writer: BsonWriter?, value: PlaceData?, encoderContext: EncoderContext?) {
        writer?.apply {
            writeStartDocument()
            writeObjectId("_id", value?._id)
            writeString("name", value?.name)
            writeDouble("latitude", value?.latitude ?: 0.0)
            writeDouble("longitude", value?.longitude ?: 0.0)
            writeString("ownerId", value?.ownerId)
            writeString("description", value?.description)
            if (value?.image != null){ writeBinaryData("image", value.image?.let { BsonBinary(it) })}
            else { writeNull("image") }
            writeEndDocument()
        }
    }

    override fun getEncoderClass(): Class<PlaceData> {
        return PlaceData::class.java
    }

    override fun decode(reader: BsonReader?, decoderContext: DecoderContext?): PlaceData? {
        reader?.apply {
            readStartDocument()
            var id: ObjectId? = null
            var name = ""
            var latitude = 0.0
            var longitude = 0.0
            var ownerId = ""
            var description: String? = null
            var image: ByteArray? = null

            while(readBsonType() != BsonType.END_OF_DOCUMENT){
                when(readName()){
                    "_id" -> id = readObjectId()
                    "name" -> name = readString()
                    "latitude" -> latitude = readDouble()
                    "longitude" -> longitude = readDouble()
                    "ownerId" -> ownerId = readString()
                    "description" -> description = if (currentBsonType == BsonType.STRING) readString() else{skipValue(); null }
                    "image" -> image = if (currentBsonType == BsonType.BINARY) readBinaryData().data else {skipValue(); null }
                }
            }
            readEndDocument()

            return PlaceData(_id=id, name=name, latitude=latitude, longitude=longitude, ownerId=ownerId, description=description, image=image)
        }
        return null
    }

}