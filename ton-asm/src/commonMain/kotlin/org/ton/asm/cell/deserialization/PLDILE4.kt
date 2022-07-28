package org.ton.asm.cell.deserialization

import org.ton.asm.Instruction
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.tlb.TlbConstructor
import org.ton.tlb.providers.TlbConstructorProvider

object PLDILE4 : Instruction, TlbConstructorProvider<PLDILE4> by PLDILE4TlbConstructor {
    override fun toString(): String = "PLDILE4"
}

private object PLDILE4TlbConstructor : TlbConstructor<PLDILE4>(
    schema = "asm_pldile4#d754 = PLDILE4;",
    type = PLDILE4::class
) {
    override fun storeTlb(cellBuilder: CellBuilder, value: PLDILE4) {
    }

    override fun loadTlb(cellSlice: CellSlice): PLDILE4 = PLDILE4
}