package com.indir.moviesdb.dto;

import lombok.Data;
import javax.validation.constraints.*;;

@Data
public class RatingDto {
    private RatingIdDto ratingId;
    @DecimalMin(value = "1.0", message = "Min rate is 1.0")
    @DecimalMax(value = "10.0", message = "Max rate is 10.0")
    @NotNull(message = "Rating can not be empty")
    private Float rating;

}
