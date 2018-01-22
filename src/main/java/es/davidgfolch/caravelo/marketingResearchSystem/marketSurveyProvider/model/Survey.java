package es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Survey implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull()
	private String subject;
	

	private Survey() {
		super();
	}
	
	public Survey(String subject) {
		this();
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}
	@JsonProperty
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}