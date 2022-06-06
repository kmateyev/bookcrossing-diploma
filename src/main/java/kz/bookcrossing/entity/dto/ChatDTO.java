package kz.bookcrossing.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatDTO {
    @JsonProperty("id")
    @ApiModelProperty(required = true, example = "11")
    private Long id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("userId")
    @ApiModelProperty(required = true, example = "1")
    private Long userId;

    @JsonProperty("created")
    @ApiModelProperty(required = true, example = "2021-09-21 12:31:58.000000")
    private Timestamp created;

    @JsonProperty("updated")
    @ApiModelProperty(required = true, example = "2021-09-21 12:31:58.000000")
    private Timestamp updated;

    @JsonProperty("shortName")
    @ApiModelProperty(required = true, example = "Test")
    private String shortName;
}
