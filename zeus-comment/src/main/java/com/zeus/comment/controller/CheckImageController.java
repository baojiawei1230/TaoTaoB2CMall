package com.zeus.comment.controller;

import com.zeus.comment.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("image")
@Controller
public class CheckImageController {
    
    @Autowired
    private ImageService imageService;

    /**
     * 页面请求验证码
     * @param response
     */
    @RequestMapping(method = RequestMethod.GET)
    public void checkImage(HttpServletResponse response) {
        
        BufferedImage bufferedImage = this.imageService.getImage();
        try {
            ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value="check",method=RequestMethod.POST)
    public ResponseEntity<Boolean> checkCode(@RequestParam("data") String code){
        try {
            Boolean boo = this.imageService.compareImageCode(code);
            if(boo){
                return ResponseEntity.ok(boo);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    

   
}
