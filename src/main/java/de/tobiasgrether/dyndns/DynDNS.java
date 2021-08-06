package de.tobiasgrether.dyndns;

import de.tobiasgrether.dyndns.model.DNSListResult;
import de.tobiasgrether.dyndns.model.DNSRecord;
import de.tobiasgrether.dyndns.model.config.ConfigModel;
import de.tobiasgrether.dyndns.model.config.RecordEntry;
import de.tobiasgrether.dyndns.util.ConfigParser;
import kong.unirest.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DynDNS {

    private final Logger logger = LoggerFactory.getLogger("DynDNS");

    private ConfigModel model;
    private String lastKnownAddress;
    private final ObjectMapper mapper = new JsonObjectMapper();

    public DynDNS() {
        try {
            this.model = ConfigParser.parseConfig(this);
        } catch (IOException e) {
            this.logger.error("Error while parsing configuration file, exiting...", e);
            return;
        }

        ScheduledExecutorService requestRoundExecutor = Executors.newSingleThreadScheduledExecutor();
        requestRoundExecutor.scheduleAtFixedRate(this::checkCurrentPublicIP, 0, 30, TimeUnit.SECONDS);
    }

    private void checkCurrentPublicIP() {
        HttpResponse<String> response = Unirest.get("https://cloudflare.com/cdn-cgi/trace")
                .asString();

        if (response.getStatus() == 200) {
            try {
                Properties p = new Properties();
                p.load(new StringReader(response.getBody()));
                String currentIp = p.getProperty("ip");
                if (!currentIp.equals(this.lastKnownAddress)) {
                    this.triggerDNSRewrite(currentIp);
                    this.lastKnownAddress = currentIp;
                }
            } catch (Throwable t) {
                this.logger.error("Error while handling ini file", t);
            }

        } else {
            this.logger.warn("Could not parse current public ip from Cloudflare. Error " + response.getStatus() + ": " + response.getStatusText());
        }
    }

    private void triggerDNSRewrite(String currentIp) {
        this.logger.warn("Detected new public ip, rewriting dns records..");

        HttpResponse<JsonNode> result = Unirest.get("https://api.cloudflare.com/client/v4/zones/" + this.model.getZoneId() + "/dns_records")
                .header("X-Auth-Key", this.model.getApiToken())
                .header("X-Auth-Email", this.model.getCfEmail())
                .asJson();

        DNSListResult parsedResult = this.mapper.readValue(result.getBody().toString(), DNSListResult.class);
        if (parsedResult.isSuccess()) {
            for (DNSRecord record : parsedResult.getResult()) {
                if (this.model.getDnsRecords().contains(new RecordEntry(record.getName(), "")) && !record.getContent().equals(currentIp)) {
                    record.setContent(currentIp);
                    HttpResponse<JsonNode> update = Unirest.put("https://api.cloudflare.com/client/v4/zones/" + this.model.getZoneId() + "/dns_records/" + record.getId())
                            .body(this.mapper.writeValue(record))
                            .header("X-Auth-Key", this.model.getApiToken())
                            .header("X-Auth-Email", this.model.getCfEmail())
                            .asJson();
                    if (update.getStatus() == 200) {
                        this.logger.info("DNS Record updated: {} (Record type {}) now has IP {}", record.getName(), record.getType(), currentIp);
                    }
                }
            }
        }
        this.logger.info("Record rewrite complete, new public ip is " + currentIp);
    }

    public Logger getLogger() {
        return logger;
    }

    public ConfigModel getModel() {
        return model;
    }
}
