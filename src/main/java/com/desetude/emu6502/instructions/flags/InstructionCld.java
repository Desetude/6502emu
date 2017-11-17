package com.desetude.emu6502.instructions.flags;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Clear Decimal Mode - set flagD to false.
 */
public class InstructionCld implements Instruction {

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        flagHolder.flagD = false;
    }

}
