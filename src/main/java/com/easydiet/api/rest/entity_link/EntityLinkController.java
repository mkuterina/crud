package com.easydiet.api.rest.entity_link;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.entity_link.EntityLink;
import com.easydiet.service.entity_link.EntityLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("entity_links")
@RequiredArgsConstructor
public class EntityLinkController {

    private final EntityLinkService entityLinkService;

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

    /*
    @GetMapping("deprecated")
    public ListEntityLinksQueryResult list() {
        try {
            List<EntityLink> entityLinks = entityLinkService.list();
            return ListEntityLinksQueryResult.success(entityLinks);
        } catch (Exception e) {
            return ListEntityLinksQueryResult.fail(e.getMessage());
        }
    }

    @GetMapping
    public ListEntityLinksQueryResult list(
            @RequestParam(value = "origin_id", required = false) String originId) {
        try {
            if (originId == null || originId.isBlank()) {
                List<EntityLink> entityLinks = entityLinkService.list();
                return ListEntityLinksQueryResult.success(entityLinks);
            } else {
                List<EntityLink> entityLinks = entityLinkService.list(originId);
                return ListEntityLinksQueryResult.success(entityLinks);
            }
        } catch (Exception e) {
            return ListEntityLinksQueryResult.fail(e.getMessage());
        }
    }

    @GetMapping
    public ListEntityLinksQueryResult list(
            @RequestParam(value = "destination_id", required = false) String destinationId) {
        try {
            if (destinationId == null || destinationId.isBlank()) {
                List<EntityLink> entityLinks = entityLinkService.list();
                return ListEntityLinksQueryResult.success(entityLinks);
            } else {
                List<EntityLink> entityLinks = entityLinkService.list(destinationId);
                return ListEntityLinksQueryResult.success(entityLinks);
            }
        } catch (Exception e) {
            return ListEntityLinksQueryResult.fail(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public EntityLinkDetailsQueryResult details(@PathVariable("id") String id) {
        try {
            EntityLink entityLink = entityLinkService.details(id);
            return EntityLinkDetailsQueryResult.success(entityLink);
        } catch (Exception e) {
            return EntityLinkDetailsQueryResult.fail(e.getMessage());
        }
    }

    @PostMapping
    public CreateEntityLinkCommandResult create(@RequestBody CreateEntityLinkCommand createEntityLinkCommand) {
        try {
            EntityLink entityLink = entityLinkService.create(createEntityLinkCommand.getDirectory_id(),
                    createEntityLinkCommand.getLinkType(), createEntityLinkCommand.getOriginId(),
                    createEntityLinkCommand.getOriginType(), createEntityLinkCommand.getDestinationId(),
                    createEntityLinkCommand.getDestinationType());
            return CreateEntityLinkCommandResult.success(entityLink);
        } catch (Exception e) {
            return CreateEntityLinkCommandResult.fail(e.getMessage());
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

    @PutMapping("{id}")
    public RetypeEntityLinkCommandResult retype(@PathVariable("id") String id,
               @RequestBody RetypeEntityLinkCommand retypeEntityLinkCommand) {
        try {
            boolean result = entityLinkService.retype(id, retypeEntityLinkCommand.getNewLinkType());
            return RetypeEntityLinkCommandResult.success(result);
        }
        catch (Exception e) {
            return RetypeEntityLinkCommandResult.fail(e.getMessage());
        }
    }
     */
}
