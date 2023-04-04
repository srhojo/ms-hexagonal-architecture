package io.github.srhojo.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(value = "io.github.srhojo.data.entities")
public class JpaConfiguration {
}
