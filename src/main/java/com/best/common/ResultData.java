package com.best.common;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by dongjianbo on 16/6/19.
*/
public class ResultData extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = -795856426054573328L;
    public static final String LIST = "list";
    public static final String NEXTPAGE = "nextpage";
    public static final String TOTAL = "total";
}
