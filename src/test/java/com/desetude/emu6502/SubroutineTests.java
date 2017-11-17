package com.desetude.emu6502;

import static com.desetude.emu6502.instructions.Instructions.*;
import static org.junit.Assert.assertEquals;

import com.desetude.emu6502.data.RegisterHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubroutineTests {

    private Emu6502 emulator;
    private RegisterHolder regHolder;
    private MMU mmu;

    @Before
    public void setup() {
        this.emulator = new Emu6502();
        this.regHolder = this.emulator.getRegHolder();
        this.mmu = this.emulator.getMmu();
    }

    @After
    public void verify() {
        CPU cpu = this.emulator.getCpu();
        while (cpu.isRunning()) {
            cpu.tick();
        }

        assertEquals(15, this.regHolder.regA);
    }

    @Test
    public void subroutineTest() {
        /*
        JSR next
        ADC #$A
        HLT
        next: ADC #$5
        RTS
         */

        this.mmu.programWrite1(0, JSR_a);
        this.mmu.programWrite2(1, this.regHolder.regPc + 6);

        this.mmu.programWrite1(3, ADC_L);
        this.mmu.programWrite1(4, 10);

        this.mmu.programWrite1(5, HLT_i);

        this.mmu.programWrite1(6, ADC_L);
        this.mmu.programWrite1(7, 5);

        this.mmu.programWrite1(8, RTS_i);
    }

}
