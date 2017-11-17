package com.desetude.emu6502.instructions.registers;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.addressing.AddressingMode;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * LoaDs index X with a value from memory.
 */
public class InstructionLdx implements Instruction {

    private final AddressingMode mode;

    public InstructionLdx(AddressingMode mode) {
        this.mode = mode;
    }

    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        regHolder.regX = this.mode.read1(mmu, regHolder);
    }

}
