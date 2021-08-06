package de.tobiasgrether.dyndns.model.config;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ConfigModel {

    public String apiToken;

    public String cfEmail;

    public String zoneId;

    public List<RecordEntry> dnsRecords;
}
