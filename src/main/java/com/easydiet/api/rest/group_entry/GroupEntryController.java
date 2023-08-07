package com.easydiet.api.rest.group_entry;

import com.easydiet.domain.group_entry.GroupEntry;
import com.easydiet.domain.group_entry.GroupEntryRepository;
import com.easydiet.service.group_entry.GroupEntryService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("group_entry")
@RequiredArgsConstructor
public class GroupEntryController {

    private final GroupEntryService groupEntryService;
    private final GroupEntryRepository groupEntryRepository;

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
    public ResponseEntity<DeleteGroupEntryCommandResult> delete(
            @PathVariable("id") String id) {
        try {
            boolean result = groupEntryService.delete(id);
            return ResponseEntity.ok(DeleteGroupEntryCommandResult.success(result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(DeleteGroupEntryCommandResult.fail(e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<GetGroupEntriesResponse> list(
            @RequestParam(value = "directoryId", required = false) String directoryId) {
        try {
            List<GroupEntry> groupEntries = groupEntryService.list(directoryId);
            return ResponseEntity.ok(GetGroupEntriesResponse.success(groupEntries));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(GetGroupEntriesResponse.fail(e));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<GetGroupEntryResponse> details(@PathVariable("id") String id) {
        try {
            GroupEntry ge = groupEntryService.findById(id);
            return ResponseEntity.ok(GetGroupEntryResponse.success(ge));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(GetGroupEntryResponse.fail(e));
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

