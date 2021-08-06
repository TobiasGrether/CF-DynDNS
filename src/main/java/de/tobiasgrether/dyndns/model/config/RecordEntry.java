package de.tobiasgrether.dyndns.model.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

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
        RecordEntry that = (RecordEntry) o;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

}
