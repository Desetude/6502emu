package com.desetude.emu6502.instructions.registers;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;

public class InstructionLdx implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        store.regX = mode.read1(bus, store);
    }
}
