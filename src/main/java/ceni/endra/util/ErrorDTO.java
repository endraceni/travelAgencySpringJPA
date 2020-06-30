package ceni.endra.util;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.Gson;


@JsonInclude(Include.NON_ABSENT)
public class ErrorDTO  implements Serializable, Representable {

		
	private static final long serialVersionUID = -3225775847421489877L;
	private String text;
	private HashMap<String, String>values;

	public ErrorDTO(String text) {
		this.text = text;
	}

	public ErrorDTO(String text, HashMap<String, String>values) {
		this.text = text;
		this.values = values;
	}
	
	public ErrorDTO addValue(String key, String value) {
		if (this.values == null) {
			this.values = new HashMap<String, String>();
		}
		this.values.put(key,  value);
		return this;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HashMap<String, String> getValues() {
		return values;
	}

	public void setValues(HashMap<String, String> values) {
		this.values = values;
	}

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}

	public String createDummyObjectForRepresentation() {
		text = null;
		values = null;
		return this.toString();
	}

}
