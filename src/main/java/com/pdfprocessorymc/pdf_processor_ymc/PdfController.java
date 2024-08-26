package com.pdfprocessorymc.pdf_processor_ymc;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfReaderService pdfReaderService;

    @PostMapping("/upload")
    public String uploadPdfAndSearchTags(
            @RequestParam("file") MultipartFile file, 
            @RequestParam("tags") List<String> tags) throws IOException {


        return pdfReaderService.readPdf(file, tags);
    }
}

