package ro.vigi.docs.repository;

import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import ro.vigi.docs.domain.Document;

@RooJpaRepository(domainType = Document.class)
public interface DocumentRepository {
}
