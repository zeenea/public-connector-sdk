package zeenea.sdk.contact;

import java.util.Objects;
import java.util.StringJoiner;

public class SourceRole {
    private final String name;

    SourceRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceRole that = (SourceRole) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SourceRole.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
