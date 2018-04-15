package com.wq.model;

public class MovieDesc extends MovieDescKey {
    private String movieHref;

    private String movieActors;

    private String movieImg;

    private String crawlerJobUrl;

    public String getMovieHref() {
        return movieHref;
    }

    public void setMovieHref(String movieHref) {
        this.movieHref = movieHref == null ? null : movieHref.trim();
    }

    public String getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(String movieActors) {
        this.movieActors = movieActors == null ? null : movieActors.trim();
    }

    public String getMovieImg() {
        return movieImg;
    }

    public void setMovieImg(String movieImg) {
        this.movieImg = movieImg == null ? null : movieImg.trim();
    }

    public String getCrawlerJobUrl() {
        return crawlerJobUrl;
    }

    public void setCrawlerJobUrl(String crawlerJobUrl) {
        this.crawlerJobUrl = crawlerJobUrl == null ? null : crawlerJobUrl.trim();
    }
}