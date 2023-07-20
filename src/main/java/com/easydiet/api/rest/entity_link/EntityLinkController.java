package com.easydiet.api.rest.entity_link;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.entity_link.*;
import com.easydiet.service.entity_link.EntityLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("entity_links")
@RequiredArgsConstructor
public class EntityLinkController {

    private final EntityLinkService entityLinkService;
    private final EntityLinkRepository entityLinkRepository;
    private final EntityTypeRepository entityTypeRepository;

    @PostMapping
    public ResponseEntity<CreateEntityLinkResponse> create(
            @RequestBody CreateEntityLinkRequest request
    ) {
        try {
            EntityLink entityLink = entityLinkService.create(
                    request.getTypeCode(),
                    request.getOriginId(),
                    request.getOriginTypeCode(),
                    request.getDestinationId(),
                    request.getDestinationTypeCode()
            );

            return ResponseEntity.ok(
                    CreateEntityLinkResponse.success(entityLink)
            );
        }
        catch (OperationForbiddenException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(CreateEntityLinkResponse.fail(e));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(CreateEntityLinkResponse.fail(e));
        }
    }

    @DeleteMapping("{id}")
    public DeleteEntityLinkCommandResult delete(@PathVariable("id") String id) {
        try {
            boolean result = entityLinkService.delete(id);
            return DeleteEntityLinkCommandResult.success(result);
        } catch (Exception e) {
            return DeleteEntityLinkCommandResult.fail(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<GetEntityLinkResponse> findAllByOriginTypeAndDestinationIdAndDestinationType(
            @RequestParam(value = "originType", required = false) String originType,
            @RequestParam(value = "destinationId", required = false) String destinationId,
            @RequestParam(value = "destinationType", required = false) String destinationType

    ) {
        try {

            List<EntityLink> entityLinks = entityLinkService.findAllByOriginTypeAndDestinationIdAndDestinationType(
                    originType,
                    destinationType,
                    destinationId
            );
            return ResponseEntity.ok(GetEntityLinkResponse.success(entityLinks));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(GetEntityLinkResponse.fail(e));
        }
    }
}
