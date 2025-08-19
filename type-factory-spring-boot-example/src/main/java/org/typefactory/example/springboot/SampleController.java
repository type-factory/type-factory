package org.typefactory.example.springboot;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Sample API")
@Validated
public class SampleController {

  @GetMapping(value = "/sample/endpoint",
      produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @Operation(summary = "Retrieves a sample something.")
  @ApiResponse(
      responseCode = "200",
      description = "A sample API.",
      content = @Content(
          mediaType = APPLICATION_JSON_VALUE,
          schema = @Schema(implementation = SampleModel.class)))
  public ResponseEntity<SampleModel> getSampleSomething() {
    return ResponseEntity.ok(new SampleModel());
  }

  public static class SampleModel {

  }


}
