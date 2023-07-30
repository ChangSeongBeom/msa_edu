package org.egovframe.cloud.portalservice.domain.portal;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PortalRepository extends JpaRepository<Portal,Long>{

    @Query(value = "SELECT id FROM Portal ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Long getLastPK();

    @Query(value = "SELECT * FROM portal p WHERE p.parent_id IS NULL and p.portal_desc IS NOT NULL GROUP BY p.portal_desc", nativeQuery = true)
    List<Portal> getPortalByDesc();

    @Query(value = "SELECT unique(p.portal_desc) FROM portal p WHERE p.portal_nm = :portalNm and p.portal_desc IS NOT NULL", nativeQuery = true)
    String getPortalDescDetail(@Param("id") String portalNm);

    @Query(value = "SELECT p.id FROM portal p WHERE p.portal_nm = :portalNm and p.parent_id IS NULL", nativeQuery = true)
    List<Long> getPortalByParentId(@Param("portalNm") String portalNm);
    @Query(value="DELETE FROM Portal WHERE id=:id",nativeQuery=true)
    void deletePortalById(@Param("id") Long id);

}

