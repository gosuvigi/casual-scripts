package com.vigi;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.RecursiveParserWrapper;
import org.apache.tika.parser.external.ExternalParser;
import org.apache.tika.parser.ocr.TesseractOCRConfig;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.AbstractRecursiveParserWrapperHandler;
import org.apache.tika.sax.BasicContentHandlerFactory;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by vigi on 10/7/2018.
 */
public class PdfOcrScript {

    public static void main(String[] args) throws Exception {
//        String[] nonOCRContains = new String[0];
//        String contents = runOCR(resource, nonOCRContains, 2,
//                BasicContentHandlerFactory.HANDLER_TYPE.TEXT, TesseractOCRConfig.OUTPUT_TYPE.TXT);
//        System.out.println(contents);
        parsePdf(args[0]);
    }

    private static void parsePdf(String resource) throws IOException, TikaException, SAXException {
        InputStream pdf = Files.newInputStream(Paths.get(resource));
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        TikaConfig config = TikaConfig.getDefaultConfig();
        BodyContentHandler handler = new BodyContentHandler(out);
        Parser parser = new AutoDetectParser(config);
        Metadata meta = new Metadata();
        ParseContext parsecontext = new ParseContext();

        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);

        TesseractOCRConfig tesserConfig = new TesseractOCRConfig();
        tesserConfig.setLanguage("eng");
        tesserConfig.setTesseractPath("C:\\Program Files\\Tesseract-OCR");
        tesserConfig.setTessdataPath("C:\\Program Files\\Tesseract-OCR\\tessdata");

        parsecontext.set(Parser.class, parser);
        parsecontext.set(PDFParserConfig.class, pdfConfig);
        parsecontext.set(TesseractOCRConfig.class, tesserConfig);

        parser.parse(pdf, handler, meta, parsecontext);
        try(OutputStream outputStream = new FileOutputStream("test-file.txt")) {
            out.writeTo(outputStream);
        }
    }


    private static boolean canRun(TesseractOCRConfig config) {
        String[] checkCmd = {config.getTesseractPath() + "tesseract.exe"};
        // If Tesseract is not on the path, do not run the test.
        return ExternalParser.check(checkCmd);
    }

    private static String runOCR(String resource, String[] nonOCRContains, int numMetadatas,
                                 BasicContentHandlerFactory.HANDLER_TYPE handlerType,
                                 TesseractOCRConfig.OUTPUT_TYPE outputType) throws Exception {
        TesseractOCRConfig config = new TesseractOCRConfig();
        config.setOutputType(outputType);

        Parser parser = new RecursiveParserWrapper(new AutoDetectParser());

        PDFParserConfig pdfConfig = new PDFParserConfig();
        pdfConfig.setExtractInlineImages(true);

        ParseContext parseContext = new ParseContext();
        parseContext.set(TesseractOCRConfig.class, config);
        parseContext.set(Parser.class, parser);
        parseContext.set(PDFParserConfig.class, pdfConfig);

        try (InputStream stream = PdfOcrScript.class.getResourceAsStream(resource)) {
            parser.parse(stream, new DefaultHandler(), new Metadata(), parseContext);
        }
        List<Metadata> metadataList = ((RecursiveParserWrapper) parser).getMetadata();

        StringBuilder contents = new StringBuilder();
        for (Metadata m : metadataList) {
            contents.append(m.get(AbstractRecursiveParserWrapperHandler.TIKA_CONTENT));
        }

        return contents.toString();
    }
}
