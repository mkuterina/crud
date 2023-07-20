package com.easydiet.api.rest.entity_link;

import com.easydiet.domain.entity_link.EntityLinkRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("entity_link_rules")
@RequiredArgsConstructor
public class EntityLinkRuleController {

    private final EntityLinkRuleRepository entityLinkRuleRepository;

    @GetMapping
    public ResponseEntity<GetEntityLinkRulesResponse> list() {
        return ResponseEntity.ok(GetEntityLinkRulesResponse.success(entityLinkRuleRepository.findAll()));
    }
}
