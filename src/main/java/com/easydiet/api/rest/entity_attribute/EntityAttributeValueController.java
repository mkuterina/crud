package com.easydiet.api.rest.entity_attribute;

import com.easydiet.domain.entity_attribute.EntityAttributeValue;
import com.easydiet.domain.entity_attribute.EntityTypeAttributeName;
import com.easydiet.service.entity_attribute.EntityAttributeValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("entity_attribute_value")
public class EntityAttributeValueController {
    private final EntityAttributeValueService entityAttributeValueService;

    @PostMapping
    public ResponseEntity<SetEntityAttributeValueResponse> setEntityAttribute(
            @RequestBody SetEntityAttributeValueRequest request
    ) {
        try {
            EntityAttributeValue entityAttributeValue = entityAttributeValueService.setEntityAttribute(
                    request.getEntityType(),
                    request.getEntityId(),
                    EntityTypeAttributeName.create(request.getEntityTypeAttributeName()),
                    request.getValue()
            );
            return ResponseEntity.ok(SetEntityAttributeValueResponse.success(entityAttributeValue)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(SetEntityAttributeValueResponse.fail(e));
        }
    }

    @DeleteMapping("{id}")
    public DeleteEntityAttributeValueCommandResult delete(@PathVariable("id") String id) {
        try {
            boolean result = entityAttributeValueService.delete(id);
            return DeleteEntityAttributeValueCommandResult.success(result);
        } catch (Exception e) {
            return DeleteEntityAttributeValueCommandResult.fail(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<GetEntityAttributeValuesResponse> findByEntityId(
            @RequestParam(value = "entityId", required = false) String entityId) {
        try {
            List<EntityAttributeValue> entityAttributeValues = entityAttributeValueService.findAllByEntityId(entityId);
            return ResponseEntity.ok(GetEntityAttributeValuesResponse.success(entityAttributeValues));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(GetEntityAttributeValuesResponse.fail(e));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<GetEntityAttributeValueResponse> details(@PathVariable("id") String id) {
        try {
            EntityAttributeValue eav = entityAttributeValueService.findByIdentifier(id);
            return ResponseEntity.ok(GetEntityAttributeValueResponse.success(eav));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(GetEntityAttributeValueResponse.fail(e));
        }
    }
}
