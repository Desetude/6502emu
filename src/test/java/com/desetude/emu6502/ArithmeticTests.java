package com.desetude.emu6502;

import static org.junit.Assert.assertEquals;

import com.desetude.emu6502.devices.Memory;
import com.desetude.emu6502.utils.MemoryUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArithmeticTests {

    private Emu6502 emulator;
    private Bus bus;
    private CpuStore store;

    @Before
    public void setup() {
        this.emulator = new Emu6502();
        this.bus = this.emulator.getBus();
        this.store = this.emulator.getStore();

        this.bus.addDevice(new Memory(0x0000, 0x0FFF, false));
    }

    @After
    public void verify() {
        Cpu cpu = this.emulator.getCpu();
        cpu.tick();

        assertEquals(15, store.regA);
    }

    @Test
    public void adc() {
        /*
        ADC #$15
         */

        MemoryUtils.programWrite(this.bus, this.store, 0x69, 15);
    }

}
