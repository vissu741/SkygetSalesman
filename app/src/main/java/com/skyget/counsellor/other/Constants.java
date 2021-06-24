package com.skyget.counsellor.other;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Base64;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import java.nio.charset.StandardCharsets;

public class Constants {

   public static final String BASE_ = "https://www.skyget.ai/";
   // public static final String BASE_ = "http://skygettesting-env.eba-qhqqzjab.ap-south-1.elasticbeanstalk.com/";

    public static final String BASE_URL = "https://www.skyget.ai/rest/";
  //  public static final String BASE_URL = "http://skyget-testing-env.eba-w8hdengf.ap-south-1.elasticbeanstalk.com/rest/";

    public static final String LOGIN_SHARED = "login_details_shared";
    public static final String SALESMAN_ID = "salesman_id";
    public static final String SALESMAN_NAME = "salesman_name";
    public static final String LOGIN_STATUS = "login_status";
    public static final String SALESMAN_STATE = "salesman_state";
    public static final String SALESMAN_MOB_NO = "salesman_mobile";
    public static final String SALESMAN_CITY = "salesman_city";
    public static final String SALESMAN_PERCENTAGE = "salesman_percentage";
    public static final String SALESMAN_VALUE = "salesman_value";


    public static final String SALESMAN_ADDRESS1 = "faculty_address1";
    public static final String SALESMAN_ADDRESS2 = "faculty_address2";
    public static final String SALESMAN_COUNTRY = "faculty_country";
    public static final String SALESMAN_BANK_DETAILS = "faculty_details";
    public static final String EMI_STATUS = "emi_status";

    public static Bitmap imageFromString(String imageData) {
        String data = imageData.substring(imageData.indexOf(",") + 1);
        byte[] imageAsBytes = Base64.decode(data.getBytes(), Base64.DEFAULT);
        String svgAsString = new String(imageAsBytes, StandardCharsets.UTF_8);

        SVG svg = null;
        try {
            svg = SVG.getFromString(svgAsString);
        } catch (SVGParseException e) {
            e.printStackTrace();
        }

        // Create a bitmap and canvas to draw onto
        float svgWidth = (svg.getDocumentWidth() != -1) ? svg.getDocumentWidth() : 500f;
        float svgHeight = (svg.getDocumentHeight() != -1) ? svg.getDocumentHeight() : 500f;

        Bitmap newBM = Bitmap.createBitmap((int) Math.round(Math.ceil(svgWidth)), (int) Math.round(Math.ceil(svgHeight)),
                Bitmap.Config.ARGB_8888);
        Canvas bmcanvas = new Canvas(newBM);

        bmcanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        // Clear background to white if you want
        //bmcanvas.drawRGB(255, 255, 255);

        // Render our document onto our canvas
        svg.renderToCanvas(bmcanvas);

        return newBM;
    }

}
