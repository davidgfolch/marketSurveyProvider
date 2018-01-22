package es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.service;

import java.util.List;

import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.Survey;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.SurveyResult;

public interface MarketSurveyProvider {

	List<Survey> getAvailableMarketSurveys(String name);
	List<SurveyResult> getMarketSurveysResults(String name);

	void add(Survey survey);
	void add(SurveyResult surveyResult);

}
