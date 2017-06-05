package com.taotao.search.bean;

import java.util.List;

public class TaotaoResult {

    private Long total;

    private List<?> data;

    public TaotaoResult() {
    }

    public TaotaoResult(Long total, List<?> data) {
        super();
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

}
