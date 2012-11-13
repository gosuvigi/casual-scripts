package ro.vigi.docs.repository;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import ro.vigi.docs.domain.DocumentType;

@RooJpaRepository(domainType = DocumentType.class)
public interface DocumentTypeRepository {
}
