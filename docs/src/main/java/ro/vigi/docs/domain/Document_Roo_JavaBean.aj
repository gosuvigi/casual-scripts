// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.vigi.docs.domain;

import ro.vigi.docs.domain.Document;
import ro.vigi.docs.domain.DocumentType;

privileged aspect Document_Roo_JavaBean {
    
    public String Document.getTitle() {
        return this.title;
    }
    
    public void Document.setTitle(String title) {
        this.title = title;
    }
    
    public String Document.getMimeType() {
        return this.mimeType;
    }
    
    public void Document.setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    
    public DocumentType Document.getDocumentType() {
        return this.documentType;
    }
    
    public void Document.setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }
    
    public byte[] Document.getFileContent() {
        return this.fileContent;
    }
    
    public void Document.setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
    
}
