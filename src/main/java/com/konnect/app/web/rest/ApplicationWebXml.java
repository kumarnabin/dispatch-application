package com.konnect.app.web.rest;

import com.konnect.app.domain.enumeration.Status;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing {@link com.konnect.app.domain.enumeration.Status}.
 */
@RestController
@RequestMapping("/api/enums")
public class EnumResource {

    private static final String ENTITY_NAME = "enum";
    private final Logger log = LoggerFactory.getLogger(EnumResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    /**
     * {@code GET  /enums} : get all the enums.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of enums in body.
     */
    @GetMapping("/statuses") // Adjust the endpoint to be more descriptive
    public ResponseEntity<Map<Integer, String>> getAllStatusEnums() {
        log.debug("REST request to get all Status enums");
        System.out.println();

        Map<Integer, String> statusMap = Arrays.stream(Status.values()).collect(Collectors.toMap(Enum::ordinal, Status::getValue));

        return ResponseEntity.ok().body(statusMap);
    }
}
