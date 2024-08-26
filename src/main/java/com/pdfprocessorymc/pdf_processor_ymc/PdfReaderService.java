package com.pdfprocessorymc.pdf_processor_ymc;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class PdfReaderService {

     public String readPdf(MultipartFile file, List<String> tags) throws IOException {

        
        if (file.isEmpty() || file == null) {
            throw new IllegalArgumentException("Arquivo esta nullo ou vazio");
        }

        if (tags.isEmpty() || tags == null) {
            throw new IllegalArgumentException("Lista de tags esta nula ou vazia");
        }

        if (file.getOriginalFilename().endsWith(".pdf")) {
            throw new IllegalArgumentException("Arquivo não é um PDF, verifique o arquivo enviado");
        }

        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        
        StringBuilder foundTags = new StringBuilder();
        for (String tag : tags) {
            if (text.contains(tag)) {
                foundTags.append("Tag encontrada: ").append(tag).append("\n");
            }
        }

        
        return foundTags.toString();
    }
}
