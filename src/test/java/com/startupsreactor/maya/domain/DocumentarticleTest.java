package com.startupsreactor.maya.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.startupsreactor.maya.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocumentarticleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Documentarticle.class);
        Documentarticle documentarticle1 = new Documentarticle();
        documentarticle1.setId(1L);
        Documentarticle documentarticle2 = new Documentarticle();
        documentarticle2.setId(documentarticle1.getId());
        assertThat(documentarticle1).isEqualTo(documentarticle2);
        documentarticle2.setId(2L);
        assertThat(documentarticle1).isNotEqualTo(documentarticle2);
        documentarticle1.setId(null);
        assertThat(documentarticle1).isNotEqualTo(documentarticle2);
    }
}
