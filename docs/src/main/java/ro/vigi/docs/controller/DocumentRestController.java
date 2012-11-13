package ro.vigi.docs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.vigi.docs.domain.Document;
import ro.vigi.docs.service.DocumentService;

@Controller
@RequestMapping("/rest/v1")
public class DocumentRestController {

	@Autowired(required = true)
	private DocumentService documentService;

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public void post(@PathVariable final Long id, final ModelMap modelMap,
			final HttpServletRequest request, final HttpServletResponse response) {
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addDocument(@RequestBody final Document document) {
		documentService.saveDocument(document);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/documents")
	@ResponseBody
	public Page<Document> getDocuments() {
		Pageable pageable = new PageRequest(0, 10, new Sort("id"));
		Page<Document> pageResult = documentService.findAllDocuments(pageable);

		return pageResult;
	}
}
