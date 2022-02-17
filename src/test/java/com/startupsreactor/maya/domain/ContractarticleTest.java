package com.startupsreactor.maya.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.startupsreactor.maya.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractarticleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Contractarticle.class);
        Contractarticle contractarticle1 = new Contractarticle();
        contractarticle1.setId(1L);
        Contractarticle contractarticle2 = new Contractarticle();
        contractarticle2.setId(contractarticle1.getId());
        assertThat(contractarticle1).isEqualTo(contractarticle2);
        contractarticle2.setId(2L);
        assertThat(contractarticle1).isNotEqualTo(contractarticle2);
        contractarticle1.setId(null);
        assertThat(contractarticle1).isNotEqualTo(contractarticle2);
    }
}
