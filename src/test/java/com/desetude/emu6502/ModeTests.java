package com.desetude.emu6502;

import static com.desetude.emu6502.instructions.Instructions.*;
import static org.junit.Assert.assertEquals;

import com.desetude.emu6502.data.RegisterHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModeTests {

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
        assertEquals(1, this.regHolder.regA);

        cpu.tick();
        assertEquals(3, this.regHolder.regA);

        cpu.tick();
        assertEquals(6, this.regHolder.regA);

        cpu.tick();
        assertEquals(10, this.regHolder.regA);

        cpu.tick();
        assertEquals(15, this.regHolder.regA);

        cpu.tick();
        assertEquals(21, this.regHolder.regA);

        cpu.tick();
        assertEquals(28, this.regHolder.regA);

        cpu.tick();
        assertEquals(36, this.regHolder.regA);
    }

    @Test
    public void adc() {
        this.mmu.memoryWrite1(0x0200, 4);
        this.mmu.memoryWrite1(0x0202, 5);
        this.mmu.memoryWrite1(0x0204, 6);

        this.mmu.memoryWrite1(0x0300, 7);
        this.mmu.memoryWrite1(0x0301, 8);
        this.mmu.memoryWrite2(0x0092, 0x0300);
        this.mmu.memoryWrite2(0x0094, 0x0301);

        this.mmu.memoryWrite1(0x00F0, 2);
        this.mmu.memoryWrite1(0x00F2, 3);

        //Attempt every mode possible for ADC

        this.mmu.programWrite1(0x00, ADC_L);
        this.mmu.programWrite1(0x01, 1);

        this.mmu.programWrite1(0x02, ADC_zp);
        this.mmu.programWrite1(0x03, 0xF0);

        this.regHolder.regX = 2;
        this.regHolder.regY = 4;

        this.mmu.programWrite1(0x04, ADC_zpX);
        this.mmu.programWrite1(0x05, 0xF0);

        this.mmu.programWrite1(0x06, ADC_a);
        this.mmu.programWrite2(0x07, 0x0200);

        this.mmu.programWrite1(0x09, ADC_aX);
        this.mmu.programWrite2(0x0A, 0x0200);

        this.mmu.programWrite1(0x0C, ADC_aY);
        this.mmu.programWrite2(0x0D, 0x0200);

        this.mmu.programWrite1(0x0F, ADC_zpIdX);
        this.mmu.programWrite1(0x10, 0x90);

        this.mmu.programWrite1(0x11, ADC_zpIdY);
        this.mmu.programWrite1(0x12, 0x90);
    }

}
