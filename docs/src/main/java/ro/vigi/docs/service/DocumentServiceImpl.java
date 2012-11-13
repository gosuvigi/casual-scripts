package ro.vigi.docs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ro.vigi.docs.domain.Document;

public class DocumentServiceImpl implements DocumentService {

	@Override
	public Page<Document> findAllDocuments(final Pageable pageable) {
		return documentRepository.findAll(pageable);
	}
}
