package com.desetude.emu6502.addressing;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.utils.MemoryUtils;

/**
 * Immediate mode - read the data straight from the program.
 */
public class ImmediateMode implements InstructionMode {

    public int read1(Bus bus, CpuStore store) {
        return MemoryUtils.programPop1(bus, store);
    }

    @Override
    public void write1(Bus bus, CpuStore store, int value) {
        throw new UnsupportedOperationException("You can not write with immediate mode");
    }

}
