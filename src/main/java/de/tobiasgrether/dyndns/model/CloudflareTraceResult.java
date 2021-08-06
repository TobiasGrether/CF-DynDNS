package de.tobiasgrether.dyndns.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CloudflareTraceResult {

    public Object fl;
    private String h;
    private String ip;
    private String ts;
    private String visit_scheme;
    private String uag;
    private String colo;
    private String http;
    private String loc;
    private String tls;
    private String sni;
    private boolean warp;
    private boolean gateway;
}
