package es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.Survey;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.model.SurveyResult;
import es.davidgfolch.caravelo.marketingResearchSystem.marketSurveyProvider.rest.SurveyRestController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MarketSurveyProviderApplicationTests {

	private static final String APPLICATION_JSON = "application/json"; // "application/json;charset=utf8"

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private Survey survey1 = new Survey("subject1");
	private Survey survey2 = new Survey("subject2");
	private final List<Survey> surveys = Arrays.asList(survey1, survey2);

	private SurveyResult surveyR1 = new SurveyResult("this is a survey result string", survey1);
	private SurveyResult surveyR2 = new SurveyResult(null, survey1);
	private final List<SurveyResult> results = Arrays.asList(surveyR1, surveyR2);

	private static boolean initData = true;

	@Before
	public void loadData() throws Exception {
		surveyR2.setResult(new URL("https://github.com/ReactiveX/RxJava"));

		if (initData) {
			initData = false;
			surveys.stream().map(this::toJsonOrNull) //
					.filter(Objects::nonNull) //
					.forEach(this::addSurvey);

			results.stream().map(this::toJsonOrNull) //
					.filter(Objects::nonNull) //
					.forEach(this::addResult);
		}
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void restCallSurveys_available() throws Exception {
		mockMvc.perform( //
				get(getURI("/available")) //
						.param("name", survey1.getSubject()) //
						.accept(MediaType.APPLICATION_JSON_UTF8)) //
				.andDo(print()) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$", hasSize(1))) //
				.andExpect(jsonPath("$[0].subject", equalTo(survey1.getSubject())));
	}

	@Test
	public void restCallSurveys_messages() throws Exception {
		mockMvc.perform( //
				get(getURI("/results")) //
						.param("name", survey1.getSubject()) //
						.accept(MediaType.APPLICATION_JSON_UTF8)) //
				.andDo(print()) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$", hasSize(results.size()))) //
				.andExpect(jsonPath("$[0].result", equalTo(surveyR1.getResult())))
				.andExpect(jsonPath("$[1].result", equalTo(((URL) surveyR2.getResult()).toString())));
	}

	public void addSurvey(String survey) {
		try {
			mockMvc.perform( //
					post(getURI("/add")) //
							.contentType(APPLICATION_JSON).content(survey))
					.andDo(print()) //
					.andExpect(status().isCreated());
		} catch (Exception e) {
			fail("Can't add result: "+survey, e);
		}
	}

	public void addResult(String result) {
		try {
			mockMvc.perform( //
					post(getURI("/addResult")) //
							.contentType(APPLICATION_JSON).content(result))
					.andDo(print()) //
					.andExpect(status().isCreated());
		} catch (Exception e) {
			fail("Can't add result: "+result, e);
		}
	}

	private <T> String toJsonOrNull(T object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getURI(String path) {
		return SurveyRestController.BASE_PATH + path;
	}

}
