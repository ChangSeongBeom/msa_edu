package org.egovframe.cloud.portalservice.service.portal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.egovframe.cloud.common.dto.RequestDto;
import org.egovframe.cloud.common.service.AbstractService;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalListResponseDto;
import org.egovframe.cloud.portalservice.api.privacy.dto.PrivacyListResponseDto;
import org.egovframe.cloud.portalservice.domain.portal.Portal;
import org.egovframe.cloud.portalservice.domain.portal.PortalRepository;
import org.egovframe.cloud.portalservice.domain.privacy.PrivacyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface PortalService{

    Iterable<Portal> getPortal();



}
