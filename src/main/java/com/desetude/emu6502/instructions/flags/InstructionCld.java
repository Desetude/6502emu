package com.desetude.emu6502.instructions.flags;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Clear Decimal Mode - set flagD to false.
 */
public class InstructionCld implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        store.flagD = false;
    }
}
