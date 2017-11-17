package com.desetude.emu6502.instructions.registers;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.addressing.AddressingMode;
import com.desetude.emu6502.data.FlagHolder;
import com.desetude.emu6502.data.RegisterHolder;
import com.desetude.emu6502.instructions.Instruction;

/**
 * Store X index in Memory
 */
public class InstructionStx implements Instruction {

    private final AddressingMode mode;

    public InstructionStx(AddressingMode mode) {
        this.mode = mode;
    }

    @Override
    public void execute(MMU mmu, RegisterHolder regHolder, FlagHolder flagHolder) {
        this.mode.write1(mmu, regHolder.regX);
    }

}
