package com.indir.moviesdb.service.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class PaginationUtils {
    static final String X_TOTAL_COUNT = "X-Total-Count";

    private PaginationUtils() {

    }

    public static <T> HttpHeaders generatePaginationHttpHeaders(Page<T> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
        return headers;
    }

}
