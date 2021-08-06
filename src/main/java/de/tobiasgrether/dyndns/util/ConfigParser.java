package de.tobiasgrether.dyndns.util;

import de.tobiasgrether.dyndns.DynDNS;
import de.tobiasgrether.dyndns.model.config.ConfigModel;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigParser {
    public static ConfigModel parseConfig(DynDNS dns) throws IOException {
        Yaml yaml = new Yaml();
        Path currentRelativePath = Paths.get("");
        File f = new File(currentRelativePath.toAbsolutePath() + "/config.yml");

        if (!f.exists()) {
            Files.copy(dns.getClass().getClassLoader().getResourceAsStream("config.yml"), f.toPath());
            dns.getLogger().warn("DEFAULT CONFIGURATION HAS BEEN GENERATED. PLEASE CONFIGURE THE FILE, THEN RUN AGAIN");
            Runtime.getRuntime().exit(0);
        }

        ConfigModel model = yaml.loadAs(new FileInputStream(f), ConfigModel.class);
        return model;
    }
}
