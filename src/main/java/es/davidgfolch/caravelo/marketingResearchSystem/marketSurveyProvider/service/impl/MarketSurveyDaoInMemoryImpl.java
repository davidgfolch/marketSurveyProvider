package es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.Survey;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.SurveyResult;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.service.MarketSurveyProvider;

@Service
public class MarketSurveyDaoInMemoryImpl implements MarketSurveyProvider {

	List<Survey> surveys = new ArrayList<>();
	List<SurveyResult> surveyResults = new ArrayList<>();

	@Override
	public List<Survey> getAvailableMarketSurveys(String name) {
		if (StringUtils.isEmpty(name))
			return surveys;
		return surveys.stream() //
				.filter(s -> s!=null && s.getSubject()!=null && s.getSubject().contains(name)) //
				.collect(Collectors.toList());
	}

	@Override
	public List<SurveyResult> getMarketSurveysResults(String name) {
		return surveyResults.stream() //
				.filter(s -> s!=null && s.getSurvey()!=null && name.equals(s.getSurvey().getSubject())) //
				.collect(Collectors.toList());
	}

	@Override
	public void add(Survey survey) {
		surveys.add(survey);
	}

	@Override
	public void add(SurveyResult surveyResult) {
		surveyResults.add(surveyResult);
	}


}
