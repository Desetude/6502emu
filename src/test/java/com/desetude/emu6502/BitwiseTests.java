package com.desetude.emu6502;

import static com.desetude.emu6502.instructions.Instructions.ADC_L;
import static com.desetude.emu6502.instructions.Instructions.AND_L;
import static com.desetude.emu6502.instructions.Instructions.ASL_A;
import static com.desetude.emu6502.instructions.Instructions.HLT_i;
import static org.junit.Assert.assertEquals;

import com.desetude.emu6502.data.RegisterHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BitwiseTests {

    private Emu6502 emulator;
    private MMU mmu;
    private RegisterHolder regHolder;

    @Before
    public void setup() {
        this.emulator = new Emu6502();
        this.mmu = this.emulator.getMmu();
        this.regHolder = this.emulator.getRegHolder();
    }

    @After
    public void verify() {
        CPU cpu = this.emulator.getCpu();

        cpu.tick();
        assertEquals(0b11010011, this.regHolder.regA);

        cpu.tick();
        assertEquals(0b10100110, this.regHolder.regA);
    }

    @Test
    public void test() {
        this.regHolder.regA = 0b11110111;

        this.mmu.programWrite1(0x0000, AND_L);
        this.mmu.programWrite1(0x0001, 0b11011011);

        this.mmu.programWrite1(0x0002, ASL_A);

        this.mmu.programWrite1(0x0003, HLT_i);
    }

}
