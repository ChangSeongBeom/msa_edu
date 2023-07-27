package org.egovframe.cloud.portalservice.domain.portal;

import org.egovframe.cloud.portalservice.api.portal.dto.ComponentResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PortalRepository2  extends JpaRepository<Portal,Long> {

     @Query(value = "SELECT * FROM portal p WHERE p.portal_nm = :portalNm AND p.parent_id IS NULL", nativeQuery = true)
     List<Portal> findPortalsByAll(@Param("portalNm") String portalNm);


     //p.id, p.type,p.content만 하면 왜 오류지
     @Query(value = "SELECT * FROM portal p WHERE p.portal_nm = :portalNm", nativeQuery = true)
     List<Portal> findComponentByAll(@Param("portalNm") String portalNm);

     @Query(value = "SELECT id FROM Portal ORDER BY id DESC LIMIT 1", nativeQuery = true)
     Long getLastPK();
}
