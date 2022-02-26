package com.example.qrgen.service;

import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QRGenerator {
    
    @GetMapping("/qr-code")
    public void generateQRCode(@RequestParam("payload") String payload,@RequestParam("height") int height, @RequestParam("width") int width, HttpServletResponse response){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(payload, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", response.getOutputStream());
        } catch (WriterException e) {
            e.printStackTrace(System.out);
            response.setStatus(500);
        } catch (IOException e) {
            e.printStackTrace(System.out);
            response.setStatus(500);
        }
    }
}
