package com.desetude.emu6502.instructions.stack;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Transfer index X to Stack pointer.
 */
public class InstructionTxs implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        store.regS = store.regX;
    }

}
