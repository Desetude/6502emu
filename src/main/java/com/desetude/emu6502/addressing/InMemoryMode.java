package com.desetude.emu6502.addressing;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;

public class InMemoryMode implements InstructionMode {

    private final int address;

    public InMemoryMode(int address) {
        this.address = address;
    }

    @Override
    public int read1(Bus bus, CpuStore store) {
        return bus.read1(this.address);
    }

    @Override
    public void write1(Bus bus, CpuStore store, int value) {
        bus.write1(this.address, value);
    }

}
