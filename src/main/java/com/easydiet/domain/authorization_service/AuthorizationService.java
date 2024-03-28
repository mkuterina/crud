package com.easydiet.domain.authorization_service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class AuthorizationService {
    private final UserWorkspaceAssignmentRepository userWorkspaceAssignmentRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;



   public List<Role> getRoles(String userId, String workspaceId) {

        List<UserWorkspaceAssignment> assignments = userWorkspaceAssignmentRepository
                .findAllByUserIdAndWorkspaceId(userId, workspaceId);

        List<Role> roles = assignments.stream().map(a -> a.getRole()).toList();
        return roles;
    }

}
