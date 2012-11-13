package ro.vigi.docs.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.vigi.docs.domain.Document;

@RequestMapping("/documents")
@Controller
@RooWebScaffold(path = "documents", formBackingObject = Document.class)
public class DocumentController {
}
