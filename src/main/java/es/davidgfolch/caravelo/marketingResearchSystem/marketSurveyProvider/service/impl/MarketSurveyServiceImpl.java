package es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.Survey;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.SurveyResult;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.service.MarketSurveyProvider;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.service.MarketSurveyService;

@Service
public class MarketSurveyServiceImpl implements MarketSurveyService {
	
	
	@Autowired
	MarketSurveyProvider provider;

	@Override
	public List<Survey> getAvailableMarketSurveys(String name) {
		return provider.getAvailableMarketSurveys(name);
	}
	
	@Override
	public List<SurveyResult> getMarketSurveysResults(String name) {
		return provider.getMarketSurveysResults(name);
	}

	@Override
	public void add(Survey survey) {
		provider.add(survey);
	}

	@Override
	public void add(SurveyResult surveyResult) {
		provider.add(surveyResult);
	}


}
