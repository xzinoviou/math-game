package com.xzinoviou.gamification.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.xzinoviou.gamification.client.dto.MultiplicationResultAttemptDTO;
import java.io.IOException;

/**
 * Deserializes a multiplication result attempt from multiplication microservice.
 *
 * @author : Xenofon Zinoviou
 */
public class MultiplicationResultAttemptDeserializer
    extends JsonDeserializer<MultiplicationResultAttemptDTO> {

  @Override
  public MultiplicationResultAttemptDTO deserialize(JsonParser jsonParser,
                                                    DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {

    ObjectCodec objectCodec = jsonParser.getCodec();
    JsonNode node = objectCodec.readTree(jsonParser);

    //TODO: Refactor this mapping in a cleaner way,
    // removing hardcoding practices.
    return new MultiplicationResultAttemptDTO(
        node.get("user").get("alias").asText(),
        node.get("multiplication").get("factorA").asInt(),
        node.get("multiplication").get("factorB").asInt(),
        node.get("resultAttempt").asInt(),
        node.get("correct").asBoolean()
    );
  }
}
