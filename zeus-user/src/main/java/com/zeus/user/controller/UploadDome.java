package com.zeus.user.controller;

import com.taotao.userinfo.bean.Location;
import com.taotao.userinfo.utils.FileUploadUtil;
import com.taotao.userinfo.utils.ImageCut;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RequestMapping("UploadDemo")
@Controller
public class UploadDome {

	  @RequestMapping(value = "/uploadHeadImage",method=RequestMethod.POST)
	    public String uploadHeadImage(HttpServletRequest request, Location location, @RequestParam(value = "imgFile") MultipartFile imageFile,
                                      Model mode ) throws Exception {
	        System.out.println("==========Start=============");
	        String realPath = request.getSession().getServletContext().getRealPath("/");
	        String resourcePath = "resources/uploadImages/";
	        
	        if(imageFile!=null){
	            if(FileUploadUtil.allowUpload(imageFile.getContentType())){
	                String fileName = FileUploadUtil.rename(imageFile.getOriginalFilename());
	                int end = fileName.lastIndexOf(".");
	                String saveName = fileName.substring(0,end);
	                File dir = new File(realPath + resourcePath);
	                if(!dir.exists()){
	                    dir.mkdirs();
	                }
	                File file = new File(dir,saveName+"_src.jpg");
	                String image=saveName+"_src.jpg";
	               
	                imageFile.transferTo(file);
	                String srcImagePath = realPath + resourcePath + saveName;
	                int imageX = Integer.parseInt(location.getX());
	                int imageY = Integer.parseInt(location.getY());
	                int imageH = Integer.parseInt(location.getH());
	                int imageW = Integer.parseInt(location.getW());
	                //这里开始截取操作
	                System.out.println("==========imageCutStart=============");
	                ImageCut.imgCut(srcImagePath,imageX,imageY,imageW,imageH , realPath);
	                System.out.println("==========imageCutEnd=============");
	            }
	        }
	        return "my-info-img";
	    }
	
}
