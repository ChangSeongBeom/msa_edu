package org.egovframe.cloud.portalservice.service.portal.impl;

import lombok.AllArgsConstructor;
import org.egovframe.cloud.portalservice.CustomMapper;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalDescResponseDto;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalLayoutRequestDto;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalLayoutResponseDto;
import org.egovframe.cloud.portalservice.domain.portal.Portal;
import org.egovframe.cloud.portalservice.domain.portal.PortalRepository;
import org.egovframe.cloud.portalservice.service.portal.PortalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class PortalImpl implements PortalService {
    private PortalRepository portalRepository;
    CustomMapper mapper=new CustomMapper();

    @Override
    public void responsePortalLayout(List<PortalLayoutRequestDto> portalLayoutRequestDtoList) {

        PortalLayoutRequestDto p= portalLayoutRequestDtoList.get(0);
        System.out.println(p);
        Long pLong= p.getId();
        String pType = p.getType();
        String pContent=p.getContent();

        Portal pPortal=Portal.builder()
                .id(pLong)
                .type(pType)
                .content(pContent)
                .build();

        portalRepository.save(pPortal);
//        Review review=Review.builder()
//                .loginId(requestReviewDto.getLoginId())
//                .contentId(requestReviewDto.getContentId())
//                .review(requestReviewDto.getReview())
//                .creDtm(now)
//                .build();



//        for(PortalLayoutRequestDto portalLayoutRequestDto: portalLayoutRequestDtoList){
//
//        }


    }

    @Override
    public List<PortalDescResponseDto> portalDescList() {
        List<Portal> portalDescList= portalRepository.getPortalByDesc();
        List<PortalDescResponseDto> result=new ArrayList<>();
        portalDescList.forEach(p->{
            result.add(mapper.strictModelMapper().map(p,PortalDescResponseDto.class));
        });
        System.out.println("result"+result);
        return result;
    }


//    @Override
//    public Iterable<Portal> getPortal( ) {
//        Iterable<Portal> reviewList=portalRepository.getPortal();
//        return reviewList;
//    }


}
