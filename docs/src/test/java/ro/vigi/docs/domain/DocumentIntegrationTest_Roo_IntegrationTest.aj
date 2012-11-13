// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ro.vigi.docs.domain;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.vigi.docs.domain.DocumentDataOnDemand;
import ro.vigi.docs.domain.DocumentIntegrationTest;
import ro.vigi.docs.repository.DocumentRepository;
import ro.vigi.docs.service.DocumentService;

privileged aspect DocumentIntegrationTest_Roo_IntegrationTest {
    
    declare @type: DocumentIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: DocumentIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: DocumentIntegrationTest: @Transactional;
    
    @Autowired
    private DocumentDataOnDemand DocumentIntegrationTest.dod;
    
    @Autowired
    DocumentService DocumentIntegrationTest.documentService;
    
    @Autowired
    DocumentRepository DocumentIntegrationTest.documentRepository;
    
    @Test
    public void DocumentIntegrationTest.testCountAllDocuments() {
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", dod.getRandomDocument());
        long count = documentService.countAllDocuments();
        Assert.assertTrue("Counter for 'Document' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void DocumentIntegrationTest.testFindDocument() {
        Document obj = dod.getRandomDocument();
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Document' failed to provide an identifier", id);
        obj = documentService.findDocument(id);
        Assert.assertNotNull("Find method for 'Document' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Document' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void DocumentIntegrationTest.testFindAllDocuments() {
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", dod.getRandomDocument());
        long count = documentService.countAllDocuments();
        Assert.assertTrue("Too expensive to perform a find all test for 'Document', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Document> result = documentService.findAllDocuments();
        Assert.assertNotNull("Find all method for 'Document' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Document' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void DocumentIntegrationTest.testFindDocumentEntries() {
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", dod.getRandomDocument());
        long count = documentService.countAllDocuments();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Document> result = documentService.findDocumentEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Document' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Document' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void DocumentIntegrationTest.testFlush() {
        Document obj = dod.getRandomDocument();
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Document' failed to provide an identifier", id);
        obj = documentService.findDocument(id);
        Assert.assertNotNull("Find method for 'Document' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyDocument(obj);
        Integer currentVersion = obj.getVersion();
        documentRepository.flush();
        Assert.assertTrue("Version for 'Document' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void DocumentIntegrationTest.testUpdateDocumentUpdate() {
        Document obj = dod.getRandomDocument();
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Document' failed to provide an identifier", id);
        obj = documentService.findDocument(id);
        boolean modified =  dod.modifyDocument(obj);
        Integer currentVersion = obj.getVersion();
        Document merged = documentService.updateDocument(obj);
        documentRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Document' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void DocumentIntegrationTest.testSaveDocument() {
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", dod.getRandomDocument());
        Document obj = dod.getNewTransientDocument(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Document' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Document' identifier to be null", obj.getId());
        documentService.saveDocument(obj);
        documentRepository.flush();
        Assert.assertNotNull("Expected 'Document' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void DocumentIntegrationTest.testDeleteDocument() {
        Document obj = dod.getRandomDocument();
        Assert.assertNotNull("Data on demand for 'Document' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Document' failed to provide an identifier", id);
        obj = documentService.findDocument(id);
        documentService.deleteDocument(obj);
        documentRepository.flush();
        Assert.assertNull("Failed to remove 'Document' with identifier '" + id + "'", documentService.findDocument(id));
    }
    
}