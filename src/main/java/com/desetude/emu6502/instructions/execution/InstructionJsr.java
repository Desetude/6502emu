package com.desetude.emu6502.instructions.execution;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;
import com.desetude.emu6502.instructions.Instruction;
import com.desetude.emu6502.utils.MemoryUtils;

/**
 * Jump, Saving Return address - Jump to execution and put
 * return address - 1 on the stack.
 */
public class InstructionJsr implements Instruction {

    @Override
    public void execute(InstructionMode mode, Bus bus, CpuStore store) {
        int subroutine = MemoryUtils.programPop2(bus, store);
        MemoryUtils.stackPush2(store.regPc - 1, bus, store);
        store.regPc = subroutine;
    }
}
