package com.desetude.emu6502.instructions;

import com.desetude.emu6502.Emu6502;
import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;

public interface Instruction {

    /**
     * Executes the instruction.
     *
     * @param mmu to use in order to interact with memory
     * @param regHolder to use in order to interact with the registers
     * @param flagHolder to use in order to interact with the flags
     */
    void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder);

}
