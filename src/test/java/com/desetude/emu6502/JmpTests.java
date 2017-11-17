package com.desetude.emu6502;

import static com.desetude.emu6502.instructions.Instructions.ADC_L;
import static com.desetude.emu6502.instructions.Instructions.HLT_i;
import static com.desetude.emu6502.instructions.Instructions.JMP_id;
import static com.desetude.emu6502.instructions.Instructions.JSR_a;
import static com.desetude.emu6502.instructions.Instructions.RTS_i;
import static org.junit.Assert.assertEquals;

import com.desetude.emu6502.data.RegisterHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JmpTests {

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
        this.mmu.memoryWrite2(0x0000, this.regHolder.regPc + 3);

        this.mmu.programWrite1(0, JSR_a);
        this.mmu.programWrite2(1, this.regHolder.regPc + 6);

        this.mmu.programWrite1(3, ADC_L);
        this.mmu.programWrite1(4, 8);

        this.mmu.programWrite1(5, HLT_i);

        this.mmu.programWrite1(6, ADC_L);
        this.mmu.programWrite1(7, 7);

        this.mmu.programWrite1(8, RTS_i);
    }

}
