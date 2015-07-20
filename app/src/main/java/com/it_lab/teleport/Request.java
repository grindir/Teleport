package com.it_lab.teleport;

/**
 * Created by alex on 13.07.15.
 */
public class Request {

    public String teg;
    public String uri;

    public String getTeg() {
        return teg;
    }

    public void setTeg(String teg) {
        this.teg = teg;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Request(String teg, String uri) {

        this.teg = teg;
        this.uri = uri;
    }
}
