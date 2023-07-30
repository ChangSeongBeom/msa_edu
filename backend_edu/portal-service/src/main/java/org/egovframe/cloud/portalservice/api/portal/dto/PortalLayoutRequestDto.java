package org.egovframe.cloud.portalservice.api.portal.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PortalLayoutRequestDto {

    public PortalLayoutRequestDto() {

    }
    private String type;
    private Long id;

    private String content;
    private String portalDesc;
    private NestedPortalListResponseDto parent;
    private Set<PortalListResponseDto> children;
}
