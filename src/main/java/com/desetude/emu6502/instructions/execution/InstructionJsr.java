package com.desetude.emu6502.instructions.execution;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Jump, Saving Return address - Jump to execution and put
 * return address - 1 on the stack.
 */
public class InstructionJsr implements Instruction {

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        int subroutine = mmu.programPop2();
        mmu.stackPush2(regHolder.regPc - 1);
        regHolder.regPc = subroutine;
    }

}
