package com.easydiet.api.rest.group_entry;

import com.easydiet.domain.group_entry.GroupEntry;
import com.easydiet.service.group_entry.GroupEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group_entry")
@RequiredArgsConstructor
public class GroupEntryController {

    private final GroupEntryService groupEntryService;

    @PostMapping
    public ResponseEntity<CreateGroupEntryResponse> create(
            @RequestBody CreateGroupEntryRequest request
    ) {
        try {
            GroupEntry groupEntry = groupEntryService.create(
                    request.getDirectoryId(),
                    request.getName(),
                    request.getType(),
                    request.getDescription()
            );
            return ResponseEntity.ok(
                    CreateGroupEntryResponse.success(groupEntry)
            );
        }
        catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(CreateGroupEntryResponse.fail(e));
        }
    }

    @DeleteMapping("{id}")
    public DeleteGroupEntryCommandResult delete(
            @PathVariable("id") String id) {
        try {
            boolean result = groupEntryService.delete(id);
            return DeleteGroupEntryCommandResult.success(result);
        } catch (Exception e) {
            return DeleteGroupEntryCommandResult.fail(e.getMessage());
        }
    }

     @GetMapping
    public GetGroupEntryResponse list(
            @RequestParam(value = "directoryId", required = false) String directoryId) {
        try {
            List<GroupEntry> groupEntries = groupEntryService.list(directoryId);
            return GetGroupEntryResponse.success(groupEntries);
        } catch (Exception e) {
            return GetGroupEntryResponse.fail(e);
        }
    }

    @GetMapping("{id}")
    public GroupEntryDetailsQueryResult details(@PathVariable("id") String id) {
        try {
            GroupEntry groupEntry = groupEntryService.details(id);
            return GroupEntryDetailsQueryResult.success(groupEntry);
        }
        catch (Exception e) {
            return GroupEntryDetailsQueryResult.fail(e.getMessage());
        }
    }

     @PutMapping("{id}")
    public RenameGroupEntryCommandResult rename(@PathVariable("id") String id,
           @RequestBody RenameGroupEntryCommand renameGroupEntryCommand) {
         try {
             boolean result = groupEntryService.rename(id, renameGroupEntryCommand.getNewName());
             return RenameGroupEntryCommandResult.success(result);
         } catch (Exception e) {
             return RenameGroupEntryCommandResult.fail(e.getMessage());
         }
     }
}
