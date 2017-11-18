package com.desetude.emu6502.addressing;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;

public interface InstructionMode {

    InstructionMode IGNORED = new IgnoredMode();
    InstructionMode IMMEDIATE = new ImmediateMode();
    InstructionMode ACCUMULATOR = new AccumulatorMode();

    static InstructionMode createInMemoryMode(int address) {
        return new InMemoryMode(address);
    }

    int read1(Bus bus, CpuStore store);

    void write1(Bus bus, CpuStore store, int value);

}
