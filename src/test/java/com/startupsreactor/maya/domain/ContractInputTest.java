package com.startupsreactor.maya.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.startupsreactor.maya.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContractInputTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractInput.class);
        ContractInput contractInput1 = new ContractInput();
        contractInput1.setId(1L);
        ContractInput contractInput2 = new ContractInput();
        contractInput2.setId(contractInput1.getId());
        assertThat(contractInput1).isEqualTo(contractInput2);
        contractInput2.setId(2L);
        assertThat(contractInput1).isNotEqualTo(contractInput2);
        contractInput1.setId(null);
        assertThat(contractInput1).isNotEqualTo(contractInput2);
    }
}
