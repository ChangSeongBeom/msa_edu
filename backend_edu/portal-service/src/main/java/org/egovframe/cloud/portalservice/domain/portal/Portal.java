package org.egovframe.cloud.portalservice.domain.portal;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.egovframe.cloud.servlet.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="portal")
@NoArgsConstructor
public class Portal extends BaseEntity {
    @Id
    @Getter
    @Setter
    @EqualsAndHashCode.Include
    private Long id;


    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String portalNm;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String portalDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Portal parent;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Setter
    private Set<Portal> children;

    @Builder
    public Portal(Long id, String type, String portalNm, String content, Portal parent) {
        this.id = id;
        this.type = type;
        this.portalNm = portalNm;
        this.content = content;
        this.parent = parent;
    }

    @JsonIgnore
    public Set<Portal> getChildren() {
        return children;
    }


}
