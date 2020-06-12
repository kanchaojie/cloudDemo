package com.kcj.user.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CellVerifyDto {

    private boolean error;

    private List<List<Map<String, Object>>> rowList = new ArrayList<>();

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<List<Map<String, Object>>> getRowList() {
        return rowList;
    }

    public void setRowList(List<List<Map<String, Object>>> rowList) {
        this.rowList = rowList;
    }

}
