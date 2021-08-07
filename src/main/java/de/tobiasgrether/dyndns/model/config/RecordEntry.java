package de.tobiasgrether.dyndns.model.config;

import de.tobiasgrether.dyndns.model.DNSRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntry {

    public String name;
    public String type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o instanceof DNSRecord) {
            DNSRecord that = (DNSRecord) o;
            return this.name.equals(that.getName()) && this.type.equals(that.getType());
        }
        return false;
    }

    public boolean compareValues(String otherName, String otherType) {
        return this.name.equals(otherName) && this.type.equals(otherType);
    }

}
