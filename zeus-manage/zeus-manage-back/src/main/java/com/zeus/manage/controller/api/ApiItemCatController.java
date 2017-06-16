package com.zeus.manage.controller.api;

import com.zeus.manage.bean.ItemCatResult;
import com.zeus.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("api/item/cat")
@Controller
public class ApiItemCatController {

    @Autowired
    private ItemCatService itemCatService;

//    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 查询所有的商品类目数据，按照前端要求的数据结构返回
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ItemCatResult> queryItemCat() {
        try {
            ItemCatResult itemCatResult = this.itemCatService.queryAllToTree();
            return ResponseEntity.ok(itemCatResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
    
    
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<String> queryItemCat(
//            @RequestParam(value = "callback", defaultValue = "") String callback) {
//        try {
//            ItemCatResult itemCatResult = this.itemCatService.queryAllToTree();
//            String json = MAPPER.writeValueAsString(itemCatResult);
//            if (StringUtils.isNotEmpty(callback)) {
//                // 需要jsonp的支持
//                return ResponseEntity.ok(callback + "(" + json + ")");
//            } else {
//                return ResponseEntity.ok(json);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//    }

}
