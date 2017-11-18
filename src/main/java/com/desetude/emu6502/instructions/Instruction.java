package com.desetude.emu6502.instructions;

import com.desetude.emu6502.Bus;
import com.desetude.emu6502.CpuStore;
import com.desetude.emu6502.addressing.InstructionMode;

public interface Instruction {

    /**
     * @return Whether this instruction requires the {@link InstructionMode} to be obtained.
     */
    default boolean obtainMode() {
        return true;
    }

    void execute(InstructionMode mode, Bus bus, CpuStore store);

}
