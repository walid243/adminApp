import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries.*
import org.bson.codecs.pojo.PojoCodecProvider
import org.litote.kmongo.getCollection

object AdminApp {
    private lateinit var client: MongoClient
    private lateinit var database: MongoDatabase
    init {
        createMongoClient()
    }

    private fun createMongoClient() {
        val pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build()
        val pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromCodecs(PlaceDataCodec(), UserDataCodec()), fromProviders(pojoCodecProvider))
        val connectionString =
            ConnectionString("mongodb+srv://walid:xaguftaxein1@cluster0.xqgvfsh.mongodb.net/?retryWrites=true&w=majority")
        val settings: MongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(
                ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build()
            )
            .build()

        client = MongoClients.create(settings)
        database = client.getDatabase("Markers").withCodecRegistry(pojoCodecRegistry)
    }

    fun getPlaceDataCollection(): MongoCollection<PlaceData> {
        return database.getCollection("PlaceData", PlaceData::class.java)
    }

    fun getUserDataCollection(): MongoCollection<UserData> {
        return database.getCollection("UserData", UserData::class.java)
    }
}