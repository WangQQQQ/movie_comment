package com.wq.model;


/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public class MovieDescKey {
    private Integer id;

    private String movieName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName == null ? null : movieName.trim();
    }
}