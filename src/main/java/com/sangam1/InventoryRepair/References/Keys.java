package com.sangam1.InventoryRepair.References;

public enum Keys {
	
    BT("BurnTimer"),
    FT("FulTimer"),
    S1S("Slot1Stack"),
    S1SN("Slot1StackSize"),
    S2S("Slot2Stack"),
    S2SN("Slot2StackSize"),
    S3S("Slot3Stack"),
    S3SN("Slot3StackSize"),
    PROG("Progress");
	
	private String key;

	Keys(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }

    
}
