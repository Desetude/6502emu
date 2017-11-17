package com.desetude.emu6502.instructions.stack;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Transfer index X to Stack pointer.
 */
public class InstructionTxs implements Instruction {

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        regHolder.regS = regHolder.regX;
    }

}
