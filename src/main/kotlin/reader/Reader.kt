package reader

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.IOException

/**
 * Created by Harmitage on 19.08.2016.
 */
object Reader {
    val JSON = jacksonObjectMapper()
    val JSONFactory = JsonFactory()
}