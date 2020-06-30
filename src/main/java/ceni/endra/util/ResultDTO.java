package ceni.endra.util;


import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;


public class ResultDTO<T> implements Serializable, Representable {

	private static final long serialVersionUID = 1309778833824224126L;
	
	protected boolean success;
	protected ArrayList<ErrorDTO> errors;
	protected ArrayList<ErrorDTO> warnings;
	protected ArrayList<ErrorDTO> info;
	protected String code;
	protected T data;
	protected Integer count;

	public ResultDTO() {
		clear();
	}

	public ResultDTO(String error) {
		clear();
		if ((error != null) && !error.isEmpty())
			addError(error);
	}
	
	public void clear() {
		data = null;
		success = false;
		count = null;
		errors = null;
		warnings = null;
		info = null;
		code = null;
	}

	public boolean harErrors() {
		return (errors != null) && (errors.size() > 0);
	}

	public ArrayList<ErrorDTO> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<ErrorDTO> errors) {
		this.errors = errors;
	}
	
	public ResultDTO<T> addError(String error) {
		return addError(new ErrorDTO(error));
	}

	public ResultDTO<T> addError(ErrorDTO error) {
		if (this.errors == null)
			this.errors = new ArrayList<ErrorDTO>();
		success = false;
		this.errors.add(error);
		return this;
	}

	public ArrayList<ErrorDTO> getWarnings() {
		return warnings;
	}

	public void setWarnings(ArrayList<ErrorDTO> warnings) {
		this.warnings = warnings;
	}

	public ResultDTO<T> addWarnings(String error) {
		return addWarnings(new ErrorDTO(error));
	}

	public ResultDTO<T> addWarnings(ErrorDTO warning) {
		if (this.warnings == null)
			this.warnings = new ArrayList<ErrorDTO>();
		this.warnings.add(warning);
		return this;
	}

	public ArrayList<ErrorDTO> getInfo() {
		return info;
	}

	public void setInfo(ArrayList<ErrorDTO> info) {
		this.info = info;
	}

	public ResultDTO<T> addInfo(String error) {
		return addInfo(new ErrorDTO(error));
	}

	public ResultDTO<T> addInfo(ErrorDTO info) {
		if (this.info == null)
			this.info = new ArrayList<ErrorDTO>();
		this.info.add(info);
		return this;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public boolean isSuccess() {
		return success;
	}

	public ResultDTO<T> success() {
		this.success = true;
		return this;
	}
	
	public ResultDTO<T> unauthorized() {
		clear();
		this.success = false;
		addError("unauthorized");
		return this;
	}
	
	/*public void setSuccess(boolean success) {
		this.success = success;
	}*/

	public T getData() {
		return data;
	}

	public ResultDTO<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString(){
		return new Gson().toJson(this);
	}

	public String createDummyObjectForRepresentation(){
		data = null;
		success = true;
		count = null;
		errors = null;
		warnings = null;
		info = null;
		code = null;
		return this.toString();
	}
}
