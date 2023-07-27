package org.egovframe.cloud.portalservice.domain.portal;

import lombok.RequiredArgsConstructor;
import org.egovframe.cloud.portalservice.api.portal.dto.PortalListResponseDto;
import org.egovframe.cloud.portalservice.domain.content.Content;
import org.egovframe.cloud.portalservice.domain.content.ContentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
@Transactional
public class PortalRepository{

    @PersistenceContext
    private final EntityManager em;


    public Iterable<Portal> getPortal(){
        return em.createQuery("select p from Portal p",Portal.class)
                .getResultList();
    }
}

