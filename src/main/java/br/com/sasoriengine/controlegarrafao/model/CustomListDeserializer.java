package br.com.sasoriengine.controlegarrafao.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomListDeserializer extends StdDeserializer<List<ClienteGarrafao>>{
	 
    public CustomListDeserializer() {
        this(null);
    }
 
    public CustomListDeserializer(Class<?> vc) {
        super(vc);
    }
 
    @Override
    public List<ClienteGarrafao> deserialize(
      JsonParser jsonparser, 
      DeserializationContext context) 
      throws IOException, JsonProcessingException {
         
        return new ArrayList();
    }
}
