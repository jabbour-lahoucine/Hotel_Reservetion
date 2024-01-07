package com.gtwo.hotelreservation.method;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class PdfGenerator {
    public static void generatePdf(String name, String email, String identification, String phoneNum, String roomCategory, String checkinDate, String checkoutDate) throws IOException, WriterException {
        String path="C:\\Users\\moham\\OneDrive\\Bureau\\Resevation_Pdfs\\reservation_ticket"+identification+".pdf";
        PdfWriter pdfWriter=new PdfWriter(path);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document=new Document(pdfDocument);

        //Background logo
        String imagePath="C:\\Users\\moham\\IdeaProjects\\HotelReservation\\src\\main\\java\\com\\gtwo\\hotelreservation\\method\\logo.png";
        ImageData imageData1= ImageDataFactory.create(imagePath);
        Image image2=new Image(imageData1);
        float x=pdfDocument.getDefaultPageSize().getWidth()/2;
        float y=pdfDocument.getDefaultPageSize().getHeight()/2;
        image2.setFixedPosition(x-150,  y);
        image2.setOpacity(0.2f);
        document.add(image2);
        //end of Background logo

        float twocol=285f;
        float twocol150=twocol+150f;
        float twocolumnWidth[]={twocol150, twocol};
        float fullwidth[] = {190f*3};
        Paragraph afterSpace = new Paragraph("\n");

        // Header
        Table table=new Table(twocolumnWidth);
        table.addCell(new Cell().add("Hotelier").setFontSize(20f).setBorder(Border.NO_BORDER).setBold());
        Table nestedtabe=new Table(new float[]{twocol/2, twocol/2});
        nestedtabe.addCell(new Cell().add("Hotelier address :").setBold().setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add("Marrakech Guiliz, 84150").setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add("email :").setBold().setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add("jbr.Hotelier@gmail.com").setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add("Phone Number :").setBold().setBorder(Border.NO_BORDER));
        nestedtabe.addCell(new Cell().add("(+212)0762403737").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(nestedtabe).setBorder(Border.NO_BORDER));
        document.add(table);

        document.add(afterSpace);

        //Border separator
        Border brd=new SolidBorder(Color.GRAY,  2f);
        Table divider=new Table(fullwidth);
        divider.setBorder(brd);
        document.add(divider);

        document.add(afterSpace);
        document.add(afterSpace);

        //body
        Table header=new Table(fullwidth);
        header.addCell(new Cell().add("Personnel information").setFontSize(15f).setBorder(Border.NO_BORDER).setBold());
        document.add(header);

        Table personnelInfoTable=new Table(new float[]{360f,360f});
        personnelInfoTable.addCell(new Cell().add("Full Name ").setBold().setBorder(Border.NO_BORDER));
        personnelInfoTable.addCell(new Cell().add(name).setBorder(Border.NO_BORDER));
        personnelInfoTable.addCell(new Cell().add("Identification (Cin/ Passport):").setBold().setBorder(Border.NO_BORDER));
        personnelInfoTable.addCell(new Cell().add(identification).setBorder(Border.NO_BORDER));
        personnelInfoTable.addCell(new Cell().add("Email ").setBold().setBorder(Border.NO_BORDER));
        personnelInfoTable.addCell(new Cell().add(email).setBorder(Border.NO_BORDER));
        personnelInfoTable.addCell(new Cell().add("Phone Number ").setBold().setBorder(Border.NO_BORDER));
        personnelInfoTable.addCell(new Cell().add(phoneNum).setBorder(Border.NO_BORDER));
        document.add(personnelInfoTable);

        document.add(afterSpace);

        Table header1=new Table(fullwidth);
        header1.addCell(new Cell().add("Reservation information").setFontSize(15f).setBorder(Border.NO_BORDER).setBold());
        document.add(header1);

        document.add(afterSpace);

        Table reservationInfoTable=new Table(new float[]{360f,360f});
        reservationInfoTable.addCell(new Cell().add("Checkin Date :").setBold().setBorder(Border.NO_BORDER));
        reservationInfoTable.addCell(new Cell().add(checkinDate).setBorder(Border.NO_BORDER));
        reservationInfoTable.addCell(new Cell().add("Checkout Date :").setBold().setBorder(Border.NO_BORDER));
        reservationInfoTable.addCell(new Cell().add(checkoutDate).setBorder(Border.NO_BORDER));
        reservationInfoTable.addCell(new Cell().add("Room category :").setBold().setBorder(Border.NO_BORDER));
        reservationInfoTable.addCell(new Cell().add(roomCategory).setBorder(Border.NO_BORDER));
        document.add(reservationInfoTable);

        document.add(afterSpace);
        document.add(afterSpace);

        document.add(new Paragraph("Welcome Mrs/Mss "+name+" to your second home. We are so happy to have you").setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Note : this document is important the day of your checkin date, to make your checkin faster ").setTextAlignment(TextAlignment.CENTER));


        // QR code image;
        String path1="C:\\Users\\moham\\OneDrive\\Bureau\\Resevation_Pdfs\\QR_code.jpg";

        String data = " welcome to our 5* hotel : Hotelier \n"+
                "full name: "+name+"\n"+
                "Email: "+email+"\n"+
                "identification: "+identification+"\n"+
                "Room category: "+roomCategory+"\n"+
                "For contacting us visit our web site : www.hotelier.com/contact";

        BitMatrix matrix =new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 150, 150);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(path1));

        Table QRcode=new Table(twocolumnWidth);
        QRcode.addCell(new Cell().add("").setBorder(Border.NO_BORDER));

        ImageData data_image = ImageDataFactory.create(path1);
        Image img = new Image(data_image);
        QRcode.addCell(new Cell().add(img).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT));
        document.add(QRcode);
        //end of QR code;

        document.add(afterSpace);

        divider.setBorder(brd);
        document.add(divider);


        //footer
        document.add(new Paragraph("Note ( Address: Marrakech Guiliz, 84150 street n°=142, Email: jbr.Hotelier@gmail.com, Phone number: (+212)0762403737").setTextAlignment(TextAlignment.CENTER));
        document.close();
    }
}
