package com.desetude.emu6502.instructions.execution;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;
import com.desetude.emu6502.utils.MemoryUtils;

public class InstructionRts implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        store.regPc = MemoryUtils.stackPop2(bus, store) + 1;
    }
}
