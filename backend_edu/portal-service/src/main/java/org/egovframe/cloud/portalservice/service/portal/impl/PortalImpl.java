package org.egovframe.cloud.portalservice.service.portal.impl;

import lombok.AllArgsConstructor;
import org.egovframe.cloud.portalservice.CustomMapper;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalDescResponseDto;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalLayoutRequestDto;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalLayoutResponseDto;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalListResponseDto;
import org.egovframe.cloud.portalservice.domain.portal.Portal;
import org.egovframe.cloud.portalservice.domain.portal.PortalRepository;
import org.egovframe.cloud.portalservice.service.portal.PortalService;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@AllArgsConstructor
public class PortalImpl implements PortalService {
    private PortalRepository portalRepository;
    CustomMapper mapper=new CustomMapper();


  @Override
    public void responsePortalLayout(String portalDescDetail,String portalNm, List<PortalLayoutRequestDto> portalLayoutRequestDtoList) {

        List<Portal> portalList = new ArrayList<>();
        for (PortalLayoutRequestDto dto : portalLayoutRequestDtoList) {

            Portal portal=Portal.builder()
                    .id(dto.getId())
                    .content(dto.getContent())
                    .type(dto.getType())
                    .portalNm(portalNm)
                    .portalDesc(portalDescDetail)
                    .build();
            portalList.add(portal);
            Portal parentPortal=mapper.strictModelMapper().map(dto,Portal.class);
            if (dto.getChildren().size()!=0){
                for(PortalListResponseDto portalListResponseDto1 : dto.getChildren()){
                    Portal portal1=Portal.builder()
                            .id(portalListResponseDto1.getId())
                            .content(portalListResponseDto1.getContent())
                            .type(portalListResponseDto1.getType())
                            .parent(parentPortal)
                            .portalNm(portalNm)
                            .build();
                    portalList.add(portal1);

                    System.out.println(portalListResponseDto1);
                    Portal componentParentPortal=mapper.strictModelMapper().map(portalListResponseDto1,Portal.class);
                    if(portalListResponseDto1.getChildren().size()!=0){
                        for(PortalListResponseDto portalListResponseDto2 : portalListResponseDto1.getChildren()){

                            Portal portal2=Portal.builder()
                                    .id(portalListResponseDto2.getId())
                                    .content(portalListResponseDto2.getContent())
                                    .type(portalListResponseDto2.getType())
                                    .parent(componentParentPortal)
                                    .portalNm(portalNm)
                                    .build();
                            portalList.add(portal2);

                        }
                    }
                    else{

                    }
                }
            }
            else{

            }
        }
      portalRepository.saveAll(portalList);


    }

    @Override
    public String portalDescDetail(String portalNm) {
        String result="";
        result=portalRepository.getPortalDescDetail(portalNm);
        return result;
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
