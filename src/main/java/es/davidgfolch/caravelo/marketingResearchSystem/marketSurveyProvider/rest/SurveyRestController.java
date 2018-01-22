package es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.Survey;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.SurveyResult;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.service.MarketSurveyService;

@RestController()
@RequestMapping(path=SurveyRestController.BASE_PATH)
public class SurveyRestController {

	public static final String BASE_PATH = "/rest/surveys/";

	@Autowired
	MarketSurveyService service;
	
	@PostMapping(path="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)	
	public void add(@RequestBody @Valid Survey survey, Model model) {
		service.add(survey);
	}
	@PostMapping(path="/addResult", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)	
	public void add(@RequestBody @Valid SurveyResult surveyResult, Model model) {
		service.add(surveyResult);
	}
	
	@GetMapping("/available")
	public List<Survey> getAvailableMarketSurveys(@RequestParam(value = "name", defaultValue = "") String name) {
		return service.getAvailableMarketSurveys(name);
	}

	@GetMapping("/results")
	public List<SurveyResult> getMarketSurveysResults(@RequestParam(value = "name") String name) {
		return service.getMarketSurveysResults(name);
	}

}