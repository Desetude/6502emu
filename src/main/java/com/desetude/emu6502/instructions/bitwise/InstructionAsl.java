package com.desetude.emu6502.instructions.bitwise;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;

public class InstructionAsl implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        int value = mode.read1(bus, store);
        mode.write1(bus, store, (value << 1) & 0xFF);
    }

}
