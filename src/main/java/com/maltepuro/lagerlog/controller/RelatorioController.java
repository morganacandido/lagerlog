package com.maltepuro.lagerlog.controller;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class RelatorioController {
    public void geraRelatorio(){
        Document documento = new Document();
        try {
          //criar o documento
          PdfWriter.getInstance(documento, new FileOutputStream("Relatorio.pdf"));
          //abrir o documento -> conte�do
          documento.open();
          documento.add(new Paragraph("Relatorio Cadastro de produtos:"));
          documento.add(new Paragraph(" "));
          //criar uma tabela
          PdfPTable tabela = new PdfPTable(2);
          PdfPTable linha = new PdfPTable(2);

          //cabeçalho
          PdfPCell col1 = new PdfPCell(new Paragraph("Codigo"));
          PdfPCell col2 = new PdfPCell(new Paragraph("Nome"));
          PdfPCell col3= new PdfPCell(new Paragraph("1"));
          PdfPCell col4= new PdfPCell(new Paragraph("Chopp"));
      
          tabela.addCell(col1);
          tabela.addCell(col2);
          linha.addCell(col3);
          linha.addCell(col4);
    
          documento.add(tabela);
          documento.add(linha);
          documento.close();

        } catch (Exception e) {
          documento.close();
        }
      }
}
