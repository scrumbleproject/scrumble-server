/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author cyril
 */
public class ScrumbleUtils {
    
    
    public static String encryptStringWithAlgorithm(String toEncrypt, String algorithm) 
            throws NoSuchAlgorithmException, UnsupportedEncodingException{
        
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(toEncrypt.getBytes("UTF-8"));
        byte digest[] = md.digest();
        return ScrumbleUtils.bytesToHex(digest);
        
    }
    
    public static String bytesToHex(byte[] b) {
      char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                         '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
      StringBuffer buf = new StringBuffer();
      for (int j=0; j<b.length; j++) {
         buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
         buf.append(hexDigit[b[j] & 0x0f]);
      }
      return buf.toString();
   }
    
}
