package com.desetude.emu6502.instructions.execution;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * ReTurn from Subroutine - Sets the program counter
 * to the top two bytes from the stack + 1.
 */
public class InstructionRts implements Instruction {

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        regHolder.regPc = mmu.stackPop2() + 1;
    }

}
