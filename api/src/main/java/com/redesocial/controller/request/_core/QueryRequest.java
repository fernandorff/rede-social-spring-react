package com.redesocial.controller.request._core;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryRequest {

    @NotBlank
    private String query;
}
