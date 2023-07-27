package org.egovframe.cloud.portalservice.domain.portal;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalListResponseDto;
import org.egovframe.cloud.portalservice.service.portal.PortalService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PortalRepositoryImpl implements PortalService {

    private PortalRepository portalRepository;


    @Override
    public Iterable<Portal> getPortal( ) {
        Iterable<Portal> reviewList=portalRepository.getPortal();
        return reviewList;
    }


}
