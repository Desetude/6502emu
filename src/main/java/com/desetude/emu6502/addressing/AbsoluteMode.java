package com.desetude.emu6502.addressing;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.RegisterHolder;

import java.util.function.Function;

/**
 * Absolute mode - reads/writes the data from the address pointed at by the program.
 */
public class AbsoluteMode implements AddressingMode {

    @Override
    public int read1(MMU mmu, RegisterHolder regHolder) {
        return mmu.memoryRead1(mmu.programPop2());
    }

    @Override
    public void write1(MMU mmu, int value) {
        mmu.memoryWrite1(mmu.programPop2(), value);
    }

    @Override
    public void modify1(MMU mmu, RegisterHolder regHolder, Function<Integer, Integer> function) {
        int address = mmu.programPop2();
        int value = mmu.memoryRead1(address);
        mmu.memoryWrite1(address, function.apply(value));
    }

}
