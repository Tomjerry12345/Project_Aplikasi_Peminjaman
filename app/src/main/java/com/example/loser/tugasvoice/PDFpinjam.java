package com.example.loser.tugasvoice;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static android.content.ContentValues.TAG;

public class PDFpinjam {
    String nama, jumlahBarang, TahunPengadaan, Status, Keterangan;

    public static String PDFpinjam(Context context, String fto, String jdl, String jbab, String ttb, String pnls, String ket)throws IOException, DocumentException {
        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Laporan Pinjam ");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            Log.i(TAG, "Created a new directory for PDF");
        }
      // ByteArrayOutputStream iBit = new ByteArrayOutputStream();
       // InputStream in = new java.net.URL(fto).openStream();
      //  Bitmap bimage = BitmapFactory.decodeStream(in);

      //  bimage.compress(Bitmap.CompressFormat.JPEG, 100, iBit);
       // Image img = Image.getInstance(new URL(fto));

       // Image img = Image.getInstance(iBit.toByteArray());

        URL url = new URL(fto);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf))) {
            out.write(buf, 0, n); }
        out.close();
        File pdfFile = new File(docsFolder.getAbsolutePath(), jdl + ".pdf");
        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        PdfWriter.getInstance(document, output);



        in.close(); byte[] response = out.toByteArray();



        document.open();

        Image img =Image.getInstance(response);
        img.setAlignment(Element.ALIGN_CENTER);
        img.scaleToFit(200, 200);
        img.setAbsolutePosition(50, 50);


        PdfPTable table = new PdfPTable(2);


        PdfPCell cell;
        cell = new PdfPCell();
        table.setTotalWidth(new float[]{ 150, 150 });
        table.setLockedWidth(true);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBorderColor(BaseColor.WHITE);
        cell.setBorder(Rectangle.NO_BORDER);
        cell.addElement(img);
        cell.setColspan(2);

        table.addCell(cell);


        table.addCell("Tanggal Pinjam");
        table.addCell(jdl);
        table.addCell("Jumlah Barang");
        table.addCell(jbab);
        table.addCell("Nama Barang");
        table.addCell(ttb);
        table.addCell("Identitas");
        table.addCell(pnls);
        table.addCell("Batas Pinjaman");
        table.addCell(ket);
        table.setWidthPercentage(50);
        document.add(table);

        document.close();

        String a = "SUKSES - LOKASI FILE : " + docsFolder.getAbsolutePath() + jdl + "pdf";
        return a;




    }




}
