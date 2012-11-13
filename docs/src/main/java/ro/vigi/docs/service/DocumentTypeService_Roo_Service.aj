// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.vigi.docs.service;

import java.util.List;
import ro.vigi.docs.domain.DocumentType;
import ro.vigi.docs.service.DocumentTypeService;

privileged aspect DocumentTypeService_Roo_Service {
    
    public abstract long DocumentTypeService.countAllDocumentTypes();    
    public abstract void DocumentTypeService.deleteDocumentType(DocumentType documentType);    
    public abstract DocumentType DocumentTypeService.findDocumentType(Long id);    
    public abstract List<DocumentType> DocumentTypeService.findAllDocumentTypes();    
    public abstract List<DocumentType> DocumentTypeService.findDocumentTypeEntries(int firstResult, int maxResults);    
    public abstract void DocumentTypeService.saveDocumentType(DocumentType documentType);    
    public abstract DocumentType DocumentTypeService.updateDocumentType(DocumentType documentType);    
}