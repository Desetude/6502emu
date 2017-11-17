package com.desetude.emu6502.addressing;

import com.desetude.emu6502.MMU;
import com.desetude.emu6502.data.RegisterHolder;

import java.util.function.Function;

public class AccumulatorMode implements AddressingMode {

    @Override
    public int read1(MMU mmu, RegisterHolder regHolder) {
        throw new UnsupportedOperationException("AccumulatorMode#read1 has not been implemented.");
    }

    @Override
    public void write1(MMU mmu, int value) {
        throw new UnsupportedOperationException("AccumulatorMode#write1 has not been implemented.");
    }

    @Override
    public void modify1(MMU mmu, RegisterHolder regHolder, Function<Integer, Integer> function) {
        regHolder.regA = function.apply(regHolder.regA) & 0xFF;
    }
}
