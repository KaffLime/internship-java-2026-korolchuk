package com.example.starter.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.slf4j.event.Level;

@ConfigurationProperties(prefix = "logging.starter")
public class LoggingProperties {

    private Level level = Level.INFO;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
