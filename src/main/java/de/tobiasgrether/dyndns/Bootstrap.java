package de.tobiasgrether.dyndns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bootstrap {
    private static final Logger logger = LoggerFactory.getLogger("Main");

    public static void main(String[] args){
        logger.info("Starting up DynDNS..");
        DynDNS dnsManager  = new DynDNS();
    }
}
