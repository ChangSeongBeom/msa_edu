package org.egovframe.cloud.portalservice.api.portal.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.*;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class PortalListResponseDto {

    public PortalListResponseDto() {

    }
    private String type;
    private Long id;

    private String content;
    private NestedPortalListResponseDto parent;
    private Set<PortalListResponseDto> children;
}
