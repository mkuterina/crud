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

    public boolean delete(String id) {
        Optional<EntityLink> optionalEntityLink = entityLinkRepository.findByIdentifier(id);
        if (optionalEntityLink.isEmpty()) {
            return false;
        }
        else {
            EntityLink entityLink = optionalEntityLink.get();
            boolean result = entityLink.delete();
            entityLinkRepository.save(entityLink);
            return result;
        }
    }

    public List<EntityLink> findAllByOriginTypeAndDestinationIdAndDestinationType(String originType,
                                                                                  String destinationType,
                                                                                  String destinationId) {
        Optional<EntityType> optionalOriginType = entityTypeRepository.findByCode(originType);
        if (optionalOriginType.isEmpty()) {
            throw new IllegalStateException();
        }
        EntityType ot = optionalOriginType.get();

        Optional<EntityType> optionalDestinationType = entityTypeRepository.findByCode(destinationType);
        if (optionalDestinationType.isEmpty()) {
            throw new IllegalStateException();
        }
        EntityType dt = optionalDestinationType.get();

        return entityLinkRepository.findAllByOriginTypeAndDestinationIdAndDestinationType(ot, dt, destinationId)
                .stream().filter(EntityLink -> !EntityLink.isDeleted()).toList();

    }
}
