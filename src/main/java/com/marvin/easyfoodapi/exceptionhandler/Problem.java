package com.marvin.easyfoodapi.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

// RFC 7807
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;
}
