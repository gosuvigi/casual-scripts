package ro.vigi.docs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.roo.addon.layers.service.RooService;

import ro.vigi.docs.domain.Document;

@RooService(domainTypes = { ro.vigi.docs.domain.Document.class })
public interface DocumentService {

	Page<Document> findAllDocuments(Pageable pageable);
}
