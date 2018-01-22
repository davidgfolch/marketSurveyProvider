package es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model;

import java.io.Serializable;

import lombok.NonNull;

public class SurveyResult implements Serializable {

	private static final long serialVersionUID = 1L;

	@NonNull
	private Object result;
	@NonNull
	private Survey survey;

	private SurveyResult() {
		super();
	}

	public SurveyResult(Object result, Survey survey) {
		this();
		this.result = result;
		this.survey = survey;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

}