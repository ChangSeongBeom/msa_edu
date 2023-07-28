package org.egovframe.cloud.portalservice.api.portal;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.egovframe.cloud.portalservice.CustomMapper;
import org.egovframe.cloud.portalservice.api.banner.dto.BannerRequestDto;
import org.egovframe.cloud.portalservice.api.portal.dto.*;
import org.egovframe.cloud.portalservice.domain.portal.Portal;
import org.egovframe.cloud.portalservice.domain.portal.PortalRepository2;

import org.egovframe.cloud.portalservice.service.portal.PortalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RestController
public class PortalAdminController {

    private final PortalService portalService;
    private final PortalRepository2 portalRepo;
    private final CustomMapper mapper;
    private Function<Portal, PortalListResponseDto> mapToPersonDTO = this::convertToDTO;

    @Autowired
    public PortalAdminController(PortalService portalService, PortalRepository2 portalRepo) {
        this.portalService = portalService;
        this.portalRepo = portalRepo;
        this.mapper = new CustomMapper();
    }
//    @GetMapping("/api/v1/portals/{id}")
//    public ResponseEntity<PortalListResponseDto> getAllDetails(@PathVariable("id") Long id) {
//        return portalRepo.findById(id).map(mapToPersonDTO).map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/api/v1/getPortals/{portalId}")
    public ResponseEntity<List<PortalListResponseDto>> getAllPortals(@PathVariable("portalId") String portalId) {
        List<Portal> portals = portalRepo.findPortalsByAll(portalId);
        System.out.println(portals);

        List<PortalListResponseDto> result = new ArrayList<>();
        portals.forEach(u -> {
            PortalListResponseDto dto = mapper.strictModelMapper().map(u, PortalListResponseDto.class);
            removeEmptyChildren(dto); // Call a method to recursively remove empty children
            result.add(dto);
        });
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/api/v1/getPortals/portalDesc")
    public List<PortalDescResponseDto> portalDescList() {
        List<PortalDescResponseDto> portalDescResponseDtoList=portalService.portalDescList();
        return portalDescResponseDtoList;
    }
    @PostMapping("/api/v1/getPortals/save")
    public ResponseEntity<List<PortalListResponseDto>> saveLayout(@RequestBody List<PortalLayoutRequestDto> portalLayoutRequestDtoList) {
        portalService.responsePortalLayout(portalLayoutRequestDtoList);

        return null;
//        List<Portal> portals = portalRepo.findPortalsByAll(portalId);
//        System.out.println(portals);
//
//        List<PortalListResponseDto> result = new ArrayList<>();
//        portals.forEach(u -> {
//            PortalListResponseDto dto = mapper.strictModelMapper().map(u, PortalListResponseDto.class);
//            removeEmptyChildren(dto); // Call a method to recursively remove empty children
//            result.add(dto);
//        });
//        return ResponseEntity.status(HttpStatus.OK).body(result);

    }
//    @GetMapping("/api/v1/components/{portalId}")
//    public ResponseEntity<List<ComponentResponseDto>> getAllComponents(@PathVariable("portalId") String portalId) {
//        List<Portal> componentResult=  portalRepo.findComponentByAll(portalId);
//
//        List<ComponentResponseDto> result = new ArrayList<>();
//        componentResult.forEach(u -> {
//            result.add(mapper.strictModelMapper().map(u,ComponentResponseDto.class));
//        });
//        //사용자를 결과물로 리턴
//        System.out.println(componentResult);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }

    @GetMapping("/api/v1/portals/lastPK")
    public Long getLastPK() {
        Long lastPK=portalRepo.getLastPK();
        return lastPK;
    }


    private void removeEmptyChildren(PortalListResponseDto dto) {
        if (dto.getChildren() != null && dto.getChildren().isEmpty()) {
            dto.setChildren(null);
        } else {
            dto.getChildren().forEach(this::removeEmptyChildren); // Recursively remove empty children
        }
    }

    private PortalListResponseDto convertToDTO(Portal portal) {
        PortalListResponseDto personDTO = PortalListResponseDto.builder()
                .id(portal.getId())
                .type(portal.getType())
                .build();

        if (portal.getChildren() != null && !portal.getChildren().isEmpty()) {
            Set<PortalListResponseDto> childrenDTO = portal.getChildren().stream()
                    .map(this::convertToDTO) // Convert each child to DTO recursively
                    .collect(Collectors.toSet());
            personDTO.setChildren(childrenDTO);
        } else {
            personDTO.setChildren(null);
        }

        return personDTO;
    }

}