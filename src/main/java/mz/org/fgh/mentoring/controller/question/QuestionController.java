package mz.org.fgh.mentoring.controller.question;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.core.annotation.Nullable;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Patch;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mz.org.fgh.mentoring.api.RESTAPIMapping;
import mz.org.fgh.mentoring.api.RestAPIResponse;
import mz.org.fgh.mentoring.base.BaseController;
import mz.org.fgh.mentoring.dto.question.QuestionDTO;
import mz.org.fgh.mentoring.entity.question.Question;
import mz.org.fgh.mentoring.entity.question.QuestionCategory;
import mz.org.fgh.mentoring.service.question.QuestionService;
@Controller(RESTAPIMapping.QUESTION)
public class QuestionController extends BaseController {
    public static final Logger LOG = LoggerFactory.getLogger(QuestionController.class);

    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Return a list off all Questions")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Get("/getAll")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Return a list of Questions given the parameters code, description and category code")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Get("/search")
    public Page<QuestionDTO> search(
            @Nullable @QueryValue("code") String code,
            @Nullable @QueryValue("description") String description,
            @Nullable @QueryValue("categoryId") Long categoryId,
            Pageable pageable
    ) {
        Page<QuestionDTO> questions = questionService.search(code, description, categoryId, pageable);
        return questions;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Return a list off all Questions")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Get("/all")
    public List<QuestionDTO> getAll() {
        return questionService.findAllQuestions();
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Save Question to database")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Post("/save")
    public HttpResponse<RestAPIResponse> create (@Body QuestionDTO questionDTO, Authentication authentication) {

        Question question = new Question(questionDTO);
        question = this.questionService.create(question, (Long) authentication.getAttributes().get("userInfo"));

        LOG.info("Created question {}", question);

        return HttpResponse.ok().body(new QuestionDTO(question));
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Get Question from database")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Get("/{id}")
    public QuestionDTO findQuestionById(@PathVariable("id") Long id){
        Question question = this.questionService.findById(id).get();
        return new QuestionDTO(question);
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Update Question to database")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Patch("/update")
    public HttpResponse<RestAPIResponse> update (@Body QuestionDTO questionDTO, Authentication authentication) {

        Question question = this.questionService.findById(questionDTO.getId()).get();
        question.setCode(questionDTO.getCode());
        question.setQuestion(questionDTO.getQuestion());
        question.setQuestionCategory(new QuestionCategory(questionDTO.getQuestionCategoryDTO()));
        question = this.questionService.update(question, (Long) authentication.getAttributes().get("userInfo"));

        LOG.info("Updated question {}", question);

        return HttpResponse.ok().body(new QuestionDTO(question));
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Get Question from database")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Patch("/{id}")
    public QuestionDTO deleteQuestion(@PathVariable("id") Long id, Authentication authentication){

        Question question = this.questionService.findById(id).get();        
        question = this.questionService.delete(question, (Long) authentication.getAttributes().get("userInfo"));       

        return new QuestionDTO(question);
    }

    
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Get Question from database")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Delete("/{id}")
    public QuestionDTO destroyQuestion(@PathVariable("id") Long id, Authentication authentication){

        Question question = this.questionService.findById(id).get();        
        this.questionService.destroy(question);       

        return new QuestionDTO(question);
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Operation(summary = "Return a list off all Questions")
    @ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Tag(name = "Question")
    @Get("/getByPageAndSize")
    public Page<QuestionDTO> getByPageAndSize(Pageable pageable) {
        return questionService.getByPageAndSize(pageable);
    }


}
