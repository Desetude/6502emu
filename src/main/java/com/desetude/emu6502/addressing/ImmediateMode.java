package com.desetude.emu6502.addressing;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.RegisterHolder;

import java.util.function.Function;

/**
 * Immediate mode - read the data straight from the program.
 */
public class ImmediateMode implements AddressingMode {

    public int read1(MMU mmu, RegisterHolder regHolder) {
        return mmu.programPop1();
    }

    @Override
    public void write1(MMU mmu, int value) {
        throw new UnsupportedOperationException("You can not write with immediate mode");
    }

    @Override public void modify1(MMU mmu, RegisterHolder regHolder, Function<Integer, Integer> function) {
        throw new UnsupportedOperationException("You cannot modify in immediate mode");
    }

}
