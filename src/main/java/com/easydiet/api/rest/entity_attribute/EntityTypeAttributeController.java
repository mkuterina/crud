package com.easydiet.api.rest.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityTypeAttribute;
import com.easydiet.service.entity_attribute.EntityTypeAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("entity_type_attribute")
public class EntityTypeAttributeController {

    private final EntityTypeAttributeService entityTypeAttributeService;
    @GetMapping
    public ResponseEntity<GetEntityTypeAttributeResponse> findByEntityTypeAndName(
            @RequestParam(value = "entityType", required = false) String entityType,
            @RequestParam(value = "name", required = false) String name
    ) {
        try {
            Optional<EntityTypeAttribute> entityTypeAttribute = entityTypeAttributeService
                    .findByEntityTypeAndName(entityType, name);
            if (entityTypeAttribute.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(GetEntityTypeAttributeResponse.success(entityTypeAttribute.get()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(GetEntityTypeAttributeResponse.fail(e));
        }
    }
}
