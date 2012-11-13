package ro.vigi.docs.domain;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Document {

	@NotNull
	@Size(max = 100)
	private String title;

	@NotNull
	@Size(max = 20)
	private String mimeType;

	@ManyToOne
	private DocumentType documentType;

	@RooUploadedFile(contentType = "application/pdf")
	@Lob
	private byte[] fileContent;
}
