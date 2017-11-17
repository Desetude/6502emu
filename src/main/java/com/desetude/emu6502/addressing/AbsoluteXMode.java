package com.desetude.emu6502.addressing;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.RegisterHolder;

import java.util.function.Function;

/**
 * Absolute Indexed with X Mode - reads/writes the data from the address
 * pointed at by the program + the value of the X register.
 */
public class AbsoluteXMode implements AddressingMode {

    @Override
    public int read1(MMU mmu, RegisterHolder regHolder) {
        return mmu.memoryRead1(mmu.programPop2() + regHolder.regX);
    }

    @Override
    public void write1(MMU mmu, int value) {
        throw new UnsupportedOperationException("AbsoluteXMode#write1 has not been implemented.");
    }

    @Override
    public void modify1(MMU mmu, RegisterHolder regHolder, Function<Integer, Integer> function) {
        int address = mmu.programPop2() + regHolder.regX;
        int value = mmu.memoryRead1(address);
        mmu.memoryWrite1(address, function.apply(value));
    }

}
