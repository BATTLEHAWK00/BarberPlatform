package cn.battlehawk233.barbershop.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamingStrategy implements PhysicalNamingStrategy {
    private String toUnderScoreCase(String text) {
        var regex = Pattern.compile("[A-Z]");
        Matcher matcher = regex.matcher(text);
        var buffer = new StringBuffer();
        boolean first = true;
        while (matcher.find()) {
            if (first) {
                matcher.appendReplacement(buffer, matcher.group(0).toLowerCase());
                first = false;
            } else {
                matcher.appendReplacement(buffer, "_" + matcher.group(0).toLowerCase());
            }
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    private Identifier withTablePrefix(Identifier identifier) {
        return Identifier.toIdentifier("bs_" + toUnderScoreCase(identifier.getText()));
    }

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return withTablePrefix(identifier);
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }
}
