package com.desetude.emu6502.instructions.execution;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * JuMP to the specified address.
 */
public class InstructionJmp implements Instruction {

    private final boolean indirect;

    /**
     * @param indirect if {@code true}, execution to the address pointed by the supplied address, otherwise execution to the supplied address
     */
    public InstructionJmp(boolean indirect) {
        this.indirect = indirect;
    }

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        int address;
        if (this.indirect) {
            address = mmu.memoryRead2(mmu.programPop2());
        } else {
            address = mmu.programPop2();
        }

        regHolder.regPc = address;
    }

}
