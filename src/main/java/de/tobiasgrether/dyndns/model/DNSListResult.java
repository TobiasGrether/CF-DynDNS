package de.tobiasgrether.dyndns.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DNSListResult {
    private boolean success;
    private DNSRecord[] result;
}
