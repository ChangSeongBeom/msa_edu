package org.egovframe.cloud.portalservice.api.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.egovframe.cloud.portalservice.domain.code.Code;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComponentResponseDto {
    private Long id;
    private String type;
    private String content;


}
