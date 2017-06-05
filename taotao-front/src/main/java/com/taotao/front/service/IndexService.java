package com.taotao.front.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.taotao.common.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IndexService {

        @Autowired
        private ApiService apiService;

        @Value("${TAOTAO_MANAGE_URL}")
        private String TAOTAO_MANAGE_URL;

        @Value("${TAOTAO_INDEX_AD1}")
        private String TAOTAO_INDEX_AD1;

        @Value("${TAOTAO_INDEX_AD2}")
        private String TAOTAO_INDEX_AD2;

        @Value("${TAOTAO_INDEX_AD3}")
        private String TAOTAO_INDEX_AD3;
        
        @Value("${TAOTAO_INDEX_AD4}")
        private String TAOTAO_INDEX_AD4;

    @Value("${TAOTAO_INDEX_LEFT1}")
    private String TAOTAO_INDEX_LEFT1;

    @Value("${TAOTAO_INDEX_LEFT2}")
    private String TAOTAO_INDEX_LEFT2;
    

        private static final ObjectMapper MAPPER = new ObjectMapper();
        
        private Map<String,Object> bigResult= new HashMap<String,Object>();

        public String queryIndexAd1() {
                try {
                        String url = TAOTAO_MANAGE_URL + TAOTAO_INDEX_AD1;
                        String jsonData = this.apiService.doGet(url);

                        // 解析数据
                        JsonNode jsonNode = MAPPER.readTree(jsonData);
                        ArrayNode rows = (ArrayNode) jsonNode.get("rows");
                        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                        for (JsonNode row : rows) {
                                Map<String, Object> map = new LinkedHashMap<String, Object>();
                                map.put("srcB", row.get("pic").asText());
                                map.put("height", 240);
                                map.put("alt", row.get("title").asText());
                                map.put("width", 670);
                                map.put("src", row.get("pic").asText());
                                map.put("widthB", 550);
                                map.put("href", row.get("url").asText());
                                map.put("heightB", 240);
                                result.add(map);
                        }
                        return MAPPER.writeValueAsString(result);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        public String queryIndexAd2() {
                try {
                        String url = TAOTAO_MANAGE_URL + TAOTAO_INDEX_AD2;
                        String jsonData = this.apiService.doGet(url);

                        // 解析数据
                        JsonNode jsonNode = MAPPER.readTree(jsonData);
                        ArrayNode rows = (ArrayNode) jsonNode.get("rows");
                        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                        for (JsonNode row : rows) {
                                Map<String, Object> map = new LinkedHashMap<String, Object>();
                                map.put("width", 310);
                                map.put("height", 70);
                                map.put("src", row.get("pic").asText());
                                map.put("href", row.get("url").asText());
                                map.put("alt", row.get("title").asText());
                                map.put("widthB", 210);
                                map.put("heightB", 70);
                                map.put("srcB", row.get("pic").asText());
                                result.add(map);
                        }
                        return MAPPER.writeValueAsString(result);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;
        }

        public List<Map<String, Object>> queryIndexAd3() {
                String url = TAOTAO_MANAGE_URL + TAOTAO_INDEX_AD3;
                try {
                        String jsondata = this.apiService.doGet(url);
                        // 解析json数据
                        // 封装数据
                        JsonNode jsonNode = MAPPER.readTree(jsondata);
                        ArrayNode rows = (ArrayNode) jsonNode.get("rows");
                        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
                        for (JsonNode row : rows) {
                                Map<String, Object> map = new LinkedHashMap<String, Object>();
                                map.put("width", 98);
                                map.put("height", 35);
                                map.put("src", row.get("pic").asText());
                                map.put("href", row.get("url").asText());
                                map.put("alt", row.get("title").asText());
                                map.put("title", row.get("title").asText());

                                result.add(map);
                        }

                        return result;
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return null;

        }

    public Object queryIndexluzleft1() {
        String url = TAOTAO_MANAGE_URL + TAOTAO_INDEX_LEFT1;
        try {
            String jsondata = this.apiService.doGet(url);
            // 解析json数据
            JsonNode jsonNode = MAPPER.readTree(jsondata);
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            for (JsonNode row : rows) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("href", row.get("url").asText());
                map.put("alt", row.get("title").asText());
                result.add(map);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object queryIndexluleft2() {
        String url = TAOTAO_MANAGE_URL + TAOTAO_INDEX_LEFT2;
        try {
            String jsondata = this.apiService.doGet(url);
            // 解析json数据
            JsonNode jsonNode = MAPPER.readTree(jsondata);
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
            for (JsonNode row : rows) {
                Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("width", 209);
                map.put("height", 155);
                map.put("data-lazyload", row.get("pic").asText());
                map.put("href", row.get("url").asText());
                map.put("alt", row.get("title").asText());
                map.put("title", row.get("title").asText());

                result.add(map);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String queryIndexAd4() {
        try {
            String url = TAOTAO_MANAGE_URL + TAOTAO_INDEX_AD4;
            String jsonData = this.apiService.doGet(url);
            // 解析数据
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            Map<String, Object> result = new LinkedHashMap<String, Object>();
            int i=0;
            for (JsonNode  row : rows) {
            	Map<String, Object> map = new LinkedHashMap<String, Object>();
                map.put("d", row.get("pic").asText());
                map.put("e", "0");
                map.put("c", row.get("subTitle").asText());
                map.put("a", row.get("url").asText());
                map.put("b", row.get("titleDesc").asText());
                map.put("f", 1);
                 i++;
                result.put( i+"", map);
            	}
            bigResult.put("1622", result);
            bigResult.put("1623", result);
            bigResult.put("1624", result);
            bigResult.put("1625", result);
            bigResult.put("1626", result);       
            
            return MAPPER.writeValueAsString(bigResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
