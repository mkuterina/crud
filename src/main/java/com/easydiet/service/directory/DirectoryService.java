package com.easydiet.service.directory;

import com.easydiet.domain.directory.*;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DirectoryService {

    private final DirectoryRepository directoryRepository;

    @Autowired
    public DirectoryService(DirectoryRepository directoryRepository) {
        this.directoryRepository = directoryRepository;

    }

    @NotNull
    public Directory create(String name, String type, String description){
        return directoryRepository.save(
                DirectoryOperations.create(
                        DirectoryName.create(name), DirectoryType.create(type), DirectoryDescription.create(description)
                )
        );
    }
    public boolean rename(String directoryId, String newName, String newType, String newDescription)
            throws DirectoryNotFoundException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);

        if (optionalDirectory.isEmpty()) {
            throw new DirectoryNotFoundException(directoryId);
        }
        else {
            Directory directory = optionalDirectory.get();
            boolean result = directory.rename(
                    DirectoryName.create(newName), DirectoryType.create(newType), DirectoryDescription.create(newDescription)
            );
            directoryRepository.save(directory);
            return result;
        }
    }
    public boolean delete(String directoryId) {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            return false;
        }
        else {
            Directory directory = optionalDirectory.get();
            boolean result = directory.delete();
            directoryRepository.save(directory);
            return result;
        }
    }
    public List<Directory> list() {
        return directoryRepository.listAll().stream()
                .filter(directory -> !directory.isDeleted())
                .toList();
    }
    public Directory details(String directoryId) throws DirectoryNotFoundException {
        Optional<Directory> optionalDirectory = directoryRepository.findByIdentifier(directoryId);
        if (optionalDirectory.isEmpty()) {
            throw new DirectoryNotFoundException(directoryId);
        }
        else {
            return optionalDirectory.get();
        }
    }
}
