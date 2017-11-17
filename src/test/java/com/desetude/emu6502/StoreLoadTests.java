package com.desetude.emu6502;

import static com.desetude.emu6502.instructions.Instructions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StoreLoadTests {

    private Emu6502 emulator;
    private RegisterHolder regHolder;
    private FlagHolder flagHolder;
    private MMU mmu;

    @Before
    public void setup() {
        this.emulator = new Emu6502();
        this.regHolder = this.emulator.getRegHolder();
        this.flagHolder = this.emulator.getFlagHolder();
        this.mmu = this.emulator.getMmu();
    }

    @After
    public void verify() {
        CPU cpu = this.emulator.getCpu();

        this.flagHolder.flagD = true;
        while (cpu.isRunning()) {
            cpu.tick();
        }

        assertEquals(15, this.regHolder.regA);
        assertEquals(15, this.mmu.memoryRead1(0x0000));
        assertEquals(15, this.regHolder.regS);
        assertEquals(15, this.regHolder.regX);
        assertEquals(15, this.regHolder.regY);
        assertFalse(this.flagHolder.flagD);
    }

    @Test
    public void loadTest() {
        this.mmu.programWrite1(0, LDA_L);
        this.mmu.programWrite1(1, 15);

        this.mmu.programWrite1(2, STA_a);
        this.mmu.programWrite2(3, 0x0000);

        this.mmu.programWrite1(5, LDX_L);

        this.mmu.programWrite1(6, 15);
        this.mmu.programWrite1(7, TXS_i);
        this.mmu.programWrite1(8, CLD_i);

        this.mmu.programWrite1(9, LDY_L);
        this.mmu.programWrite1(10, 15);

        this.mmu.programWrite1(11, HLT_i);
    }

}
