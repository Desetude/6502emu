package com.desetude.emu6502;

import com.desetude.emu6502.devices.Device;
import com.desetude.emu6502.exceptions.BusException;

import java.util.NavigableSet;
import java.util.Optional;
import java.util.TreeSet;

public class Bus {

    private final NavigableSet<Device> devices;

    public Bus() {
        this.devices = new TreeSet<>();
    }

    public void addDevice(Device device) {
        this.devices.add(device);
    }

    public Optional<Device> getMemoryHolderOpt(int address) {
        for (Device device : this.devices) {
            if (device.includes(address)) {
                return Optional.of(device);
            }
        }

        return Optional.empty();
    }

    public Device getMemoryHolder(int address) {
        return getMemoryHolderOpt(address)
                .orElseThrow(() -> new BusException("Failed to get memory holder of address: 0x%04X", address));
    }

    public int read1(int address) {
        address &= 0xFFFF;

        Device device = getMemoryHolder(address);
        return device.read(address - device.getStartAddress()) & 0xFF;
    }

    public int read2(int address) {
        return (read1(address) & 0xFF) | (read1(address + 1) << 8);
    }

    public void write1(int address, int value) {
        Device device = getMemoryHolderOpt(address)
                .orElseThrow(() -> new BusException("Failed to get memory holder of address: 0x%04X", address));

        device.write(address & 0xFFFF, value & 0xFF);
    }

    public void write2(int address, int value) {
        write1(address, value & 0xFF);
        write1(address + 1, value >> 8);
    }

}
