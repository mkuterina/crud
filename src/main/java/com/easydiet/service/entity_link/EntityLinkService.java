package com.easydiet.service.entity_link;

import com.easydiet.domain.OperationForbiddenException;
import com.easydiet.domain.entity_link.*;
import com.easydiet.service.ConsistencyViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntityLinkService {

    private final EntityLinkRepository entityLinkRepository;
    private final EntityTypeRepository entityTypeRepository;
    private final EntityLinkTypeRepository entityLinkTypeRepository;
    private final EntityLinkCreationPolicyRepository entityLinkCreationPolicyRepository;
    private final EntityLinkOperations entityLinkOperations;
    public EntityLink create(
            String typeCode,
            String originId,
            String originTypeCode,
            String destinationId,
            String destinationTypeCode
    ) throws
            ConsistencyViolationException,
            OperationForbiddenException
    {
        Optional<EntityLinkType> entityLinkType = entityLinkTypeRepository.findByCode(typeCode);
        Optional<EntityType> originType = entityTypeRepository.findByCode(originTypeCode);
        Optional<EntityType> destinationType = entityTypeRepository.findByCode(destinationTypeCode);

        if (entityLinkType.isEmpty()) {
            throw new ConsistencyViolationException("Заданный тип связи не существует");
        }

        if (originType.isEmpty()) {
            throw new ConsistencyViolationException("Заданный тип origin не существует");
        }

        if (destinationType.isEmpty()) {
            throw new ConsistencyViolationException("Заданный тип destination не существует");
        }

        List<EntityLink> alreadyCreatedLinks = entityLinkRepository.findAllByTypeAndOriginIdAndOriginTypeAndDestinationIdAndDestinationType(
                entityLinkType.get(),
                originId,
                originType.get(),
                destinationId,
                destinationType.get()
        );

        EntityLinkCreationPolicy policy = entityLinkCreationPolicyRepository.findByLinkType(entityLinkType.get());

        EntityLink entityLink = entityLinkOperations.createEntityLink(
                entityLinkType.get(),
                originId,
                originType.get(),
                destinationId,
                destinationType.get(),
                policy,
                alreadyCreatedLinks
        );

        entityLinkRepository.saveAndFlush(entityLink);

        return entityLink;
    }
}

