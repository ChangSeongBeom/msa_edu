package org.egovframe.cloud.portalservice.api.portal.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
public class NestedPortalListResponseDto {
//    private Long portalNo;
//
//    private Long portalId;

    private String type;
    private Long id;
    private Set<NestedPortalListResponseDto> children;
//    private NestedPortalListResponseDto parent;
//    private Set<NestedPortalListResponseDto> children;


}