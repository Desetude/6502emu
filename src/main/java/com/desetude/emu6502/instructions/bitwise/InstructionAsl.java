package com.desetude.emu6502.instructions.bitwise;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.addressing.AddressingMode;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Bitwise (Arithmetic) Shift Left instruction.
 */
public class InstructionAsl implements Instruction {

    private final AddressingMode mode;

    public InstructionAsl(AddressingMode mode) {
        this.mode = mode;
    }

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        this.mode.modify1(mmu, regHolder, number -> (number << 1 & 0xFF));
    }

}
