package org.egovframe.cloud.portalservice.domain.portal;

import lombok.RequiredArgsConstructor;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalListResponseDto;
import org.egovframe.cloud.portalservice.domain.content.Content;
import org.egovframe.cloud.portalservice.domain.content.ContentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public interface PortalRepository extends JpaRepository<Portal,Long>{

    @Query(value = "SELECT id FROM Portal ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Long getLastPK();

    @Query(value = "SELECT * FROM portal p WHERE p.parent_id IS NULL and p.portal_desc IS NOT NULL", nativeQuery = true)
    List<Portal> getPortalByDesc();
}

