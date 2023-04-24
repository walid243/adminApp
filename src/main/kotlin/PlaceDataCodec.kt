import org.bson.BsonBinary
import org.bson.BsonReader
import org.bson.BsonType
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext

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

    override fun decode(reader: BsonReader?, decoderContext: DecoderContext?): PlaceData {
        reader?.apply {
            readStartDocument()
            val id = readObjectId("_id")
            val name = readString("name")
            val latitude = readDouble("latitude")
            val longitude = readDouble("longitude")
            val ownerId = readString("ownerId")
            val description =if (readBsonType() == BsonType.STRING) {
                readString("description")
            } else {
                null
            }
            val image = readBinaryData("image")?.data
            readEndDocument()

            return PlaceData(_id=id, name=name, latitude=latitude, longitude=longitude, ownerId=ownerId, description=description, image=image)
        }
        return PlaceData()
    }

}