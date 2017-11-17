package com.desetude.emu6502.instructions.bitwise;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.addressing.AddressingMode;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Bitwise AND instruction.
 */
public class InstructionAnd implements Instruction {

    private final AddressingMode mode;

    public InstructionAnd(AddressingMode mode) {
        this.mode = mode;
    }

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        regHolder.regA &= this.mode.read1(mmu, regHolder);
    }

}
