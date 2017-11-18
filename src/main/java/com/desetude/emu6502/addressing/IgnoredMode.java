package com.desetude.emu6502.addressing;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;

public class IgnoredMode implements InstructionMode {

    @Override
    public int read1(Bus bus, CpuStore store) {
        throw new UnsupportedOperationException("You can not read from IgnoredMode.");
    }

    @Override
    public void write1(Bus bus, CpuStore store, int value) {
        throw new UnsupportedOperationException("You can not write to IgnoredMode.");
    }
}
