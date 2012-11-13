// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.vigi.docs.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.vigi.docs.domain.Document;
import ro.vigi.docs.domain.DocumentDataOnDemand;
import ro.vigi.docs.domain.DocumentTypeDataOnDemand;
import ro.vigi.docs.repository.DocumentRepository;
import ro.vigi.docs.service.DocumentService;

privileged aspect DocumentDataOnDemand_Roo_DataOnDemand {
    
    declare @type: DocumentDataOnDemand: @Component;
    
    private Random DocumentDataOnDemand.rnd = new SecureRandom();
    
    private List<Document> DocumentDataOnDemand.data;
    
    @Autowired
    private DocumentTypeDataOnDemand DocumentDataOnDemand.documentTypeDataOnDemand;
    
    @Autowired
    DocumentService DocumentDataOnDemand.documentService;
    
    @Autowired
    DocumentRepository DocumentDataOnDemand.documentRepository;
    
    public Document DocumentDataOnDemand.getNewTransientDocument(int index) {
        Document obj = new Document();
        setFileContent(obj, index);
        setMimeType(obj, index);
        setTitle(obj, index);
        return obj;
    }
    
    public void DocumentDataOnDemand.setFileContent(Document obj, int index) {
        byte[] fileContent = String.valueOf(index).getBytes();
        obj.setFileContent(fileContent);
    }
    
    public void DocumentDataOnDemand.setMimeType(Document obj, int index) {
        String mimeType = "mimeType_" + index;
        if (mimeType.length() > 20) {
            mimeType = mimeType.substring(0, 20);
        }
        obj.setMimeType(mimeType);
    }
    
    public void DocumentDataOnDemand.setTitle(Document obj, int index) {
        String title = "title_" + index;
        if (title.length() > 100) {
            title = title.substring(0, 100);
        }
        obj.setTitle(title);
    }
    
    public Document DocumentDataOnDemand.getSpecificDocument(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Document obj = data.get(index);
        Long id = obj.getId();
        return documentService.findDocument(id);
    }
    
    public Document DocumentDataOnDemand.getRandomDocument() {
        init();
        Document obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return documentService.findDocument(id);
    }
    
    public boolean DocumentDataOnDemand.modifyDocument(Document obj) {
        return false;
    }
    
    public void DocumentDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = documentService.findDocumentEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Document' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Document>();
        for (int i = 0; i < 10; i++) {
            Document obj = getNewTransientDocument(i);
            try {
                documentService.saveDocument(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            documentRepository.flush();
            data.add(obj);
        }
    }
    
}
