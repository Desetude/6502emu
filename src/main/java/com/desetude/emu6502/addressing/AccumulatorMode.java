package com.desetude.emu6502.addressing;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;

public class AccumulatorMode implements InstructionMode {

    @Override
    public int read1(Bus bus, CpuStore store) {
        return store.regA;
    }

    @Override
    public void write1(Bus bus, CpuStore store, int value) {
        store.regA = value;
    }

}
