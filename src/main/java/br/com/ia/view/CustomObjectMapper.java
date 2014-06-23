package br.com.ia.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@SuppressWarnings("serial")
public class CustomObjectMapper extends ObjectMapper {
	public CustomObjectMapper() {
		super();
		Hibernate4Module hm = new Hibernate4Module();
		hm.configure(com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature.FORCE_LAZY_LOADING, true);
		registerModule(hm);
        this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        this.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}

	public void setPrettyPrint(boolean prettyPrint) {
	//	configure(SerializationFeature.INDENT_OUTPUT, prettyPrint);
	}
}