package com.desetude.emu6502;

import static com.desetude.emu6502.instructions.Instructions.ADC_L;
import static com.desetude.emu6502.instructions.Instructions.ADC_a;
import static com.desetude.emu6502.instructions.Instructions.ADC_zp;
import static com.desetude.emu6502.instructions.Instructions.HLT_i;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArithmeticTests {

    private Emu6502 emulator;
    private MMU mmu;

    @Before
    public void setup() {
        this.emulator = new Emu6502();
        this.mmu = this.emulator.getMmu();
    }

    @After
    public void verify() {
        CPU cpu = this.emulator.getCpu();
        while (cpu.isRunning()) {
            cpu.tick();
        }

        assertEquals(15, this.emulator.getRegHolder().regA);
    }

    @Test
    public void adc() {
        this.mmu.programWrite1(0x0000, ADC_L);
        this.mmu.programWrite1(0x0001, 15);

        this.mmu.programWrite1(0x0002, HLT_i);
    }

}
