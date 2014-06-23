package br.com.ia.view;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/*import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;*/

public class DeserializadorData extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)	throws IOException, JsonProcessingException {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String data = jp.getText();
		try {
			return formatter.parse(data);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
