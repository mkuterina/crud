package com.easydiet.api.rest.directory;

import com.easydiet.domain.directory.Directory;
import com.easydiet.service.directory.DirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("directory")
public class DirectoryController {
    private final DirectoryService directoryService;

    @Autowired
    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping
    public ListDirectoriesQueryResult list() {
        try {
            List<Directory> directory = directoryService.list();
            return ListDirectoriesQueryResult.success(directory);
        } catch (Exception e) {
            return ListDirectoriesQueryResult.fail(e.getMessage());
        }
    }

    @GetMapping(value = "{directory_id}")
    public DirectoryDetailsQueryResult details(@PathVariable("directory_id") String directory_id) {
        try {
            Directory directory = directoryService.details(directory_id);
            return DirectoryDetailsQueryResult.success(directory);
        } catch (Exception e) {
            return DirectoryDetailsQueryResult.fail(e.getMessage());
        }
    }

    @PostMapping
    public CreteDirectoryCommandResult create(@RequestBody CreateDirectoryCommand
                                                          createDirectoryCommand) {
        try {
            Directory directory = directoryService.create(createDirectoryCommand.getName(),
                    createDirectoryCommand.getType(), createDirectoryCommand.getDescription());
            return CreteDirectoryCommandResult.success(directory);
        }
        catch (Exception e) {
            return CreteDirectoryCommandResult.fail(e.getMessage());
        }
    }
    @DeleteMapping(value = "{directory_id}")
    public DeleteDirectoryCommandResult delete(@PathVariable("directory_id") String directory_id) {
        try {
            boolean result = directoryService.delete(directory_id);
            return DeleteDirectoryCommandResult.success(result);
        }
        catch (Exception e) {
            return DeleteDirectoryCommandResult.fail(e.getMessage());
        }
    }
    @PutMapping("{directory_id}")
    public RenameDirectoryCommandResult rename(@PathVariable("id") String directory_id,
                                               @RequestBody RenameDirectoryCommand renameDirectoryCommand) {
        try {
            boolean result = directoryService.rename(directory_id, renameDirectoryCommand.getNewName(),
                    renameDirectoryCommand.getNewType(), renameDirectoryCommand.getNewDescription());
            return RenameDirectoryCommandResult.success(result);
        }
        catch (Exception e) {
            return RenameDirectoryCommandResult.fail(e.getMessage());
        }
    }
}
