package org.mesibo.messenger;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

public class TestOCR {
    public static void main(String[] args) throws IOException {
        System.out.println(imageOCR(new File("testPic.jpg")));
    }
    public static String imageOCR(File f){
        Tesseract tesseract = new Tesseract();
        try {

            tesseract.setDatapath("./tessdata");

            String text = tesseract.doOCR(f);
            return text;
        }
        catch (TesseractException e) {
            e.printStackTrace();
            return "";
        }
    }
}
