package de.tobiasgrether.dyndns.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DNSRecord {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("proxiable")
    @Expose
    private Boolean proxiable;
    @SerializedName("proxied")
    @Expose
    private Boolean proxied;
    @SerializedName("ttl")
    @Expose
    private Integer ttl;
    @SerializedName("locked")
    @Expose
    private Boolean locked;
    @SerializedName("zone_id")
    @Expose
    private String zoneId;
    @SerializedName("zone_name")
    @Expose
    private String zoneName;
    @SerializedName("modified_on")
    @Expose
    private String modifiedOn;
    @SerializedName("created_on")
    @Expose
    private String createdOn;

    public DNSRecord() {
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    public Boolean getProxiable() {
        return this.proxiable;
    }

    public Boolean getProxied() {
        return this.proxied;
    }

    public Integer getTtl() {
        return this.ttl;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public String getZoneId() {
        return this.zoneId;
    }

    public String getZoneName() {
        return this.zoneName;
    }

    public String getModifiedOn() {
        return this.modifiedOn;
    }

    public String getCreatedOn() {
        return this.createdOn;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProxiable(Boolean proxiable) {
        this.proxiable = proxiable;
    }

    public void setProxied(Boolean proxied) {
        this.proxied = proxied;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}

