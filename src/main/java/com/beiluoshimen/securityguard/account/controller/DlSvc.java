package com.beiluoshimen.securityguard.account.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.beiluoshimen.securityguard.account.client.DlSvcApi;


@Controller
public class DlSvc {
//PROJECT FULLPATH
	private String projectPath = "/Users/Andy/Documents/workspace/SecurityGuardServer";
//	private String projectPath = "";
	
    // Path of the files to be downloaded, relative to application's directory
    private String filePath100 = projectPath + "/assets/100.zip";
    private String filePath101 = projectPath + "/assets/101.zip";
    private String filePath102 = projectPath + "/assets/102.zip";
    private String filePath103 = projectPath + "/assets/103.zip";
    //Size of a byte buffer to read/write file
    private static final int BUFFER_SIZE = 4096;
    
    //open browser, type in :https://localhost:8443/download/100.zip 
    //as test...
    
    //Method for handling file download request from client  
    @RequestMapping(value = DlSvcApi.DL_100_PATH,method = RequestMethod.GET)
    public void doDownload100(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
    	
        // get absolute path of the application
        ServletContext context = request.getServletContext();
//        String appPath = context.getRealPath("");
//        System.out.println("appPath = " + appPath);
 
//         construct the complete absolute path of the file
//        String fullPath = appPath + filePath100;
        String fullPath = filePath100;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
    
    }

    
    //Method for handling file download request from client  
    @RequestMapping(value = DlSvcApi.DL_101_PATH,method = RequestMethod.GET)
    public void doDownload101(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
    	
        // get absolute path of the application
        ServletContext context = request.getServletContext();
//        String appPath = context.getRealPath("");
        String appPath = context.getContextPath();
        System.out.println("appPath = " + appPath);
 
        // construct the complete absolute path of the file
//        String fullPath = appPath + filePath101;      
        String fullPath = filePath101;      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
    
    }

    
    //Method for handling file download request from client  
    @RequestMapping(value = DlSvcApi.DL_102_PATH,method = RequestMethod.GET)
    public void doDownload102(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
    	
        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
 
        // construct the complete absolute path of the file
//        String fullPath = appPath + filePath102;      
        String fullPath = filePath102;      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
    
    }

    
    //Method for handling file download request from client  
    @RequestMapping(value = DlSvcApi.DL_103_PATH,method = RequestMethod.GET)
    public void doDownload103(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
    	
        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
 
        // construct the complete absolute path of the file
//        String fullPath = appPath + filePath103;      
        String fullPath =  filePath103;      
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 
        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
    
    }

	
}
