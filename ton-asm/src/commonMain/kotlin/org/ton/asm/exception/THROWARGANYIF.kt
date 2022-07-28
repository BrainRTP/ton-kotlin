package org.ton.asm.exception

import org.ton.asm.Instruction
import org.ton.cell.CellBuilder
import org.ton.cell.CellSlice
import org.ton.tlb.TlbConstructor
import org.ton.tlb.providers.TlbConstructorProvider

object THROWARGANYIF : Instruction, TlbConstructorProvider<THROWARGANYIF> by THROWARGANYIFTlbConstructor {
    override fun toString(): String = "THROWARGANYIF"
}

private object THROWARGANYIFTlbConstructor : TlbConstructor<THROWARGANYIF>(
    schema = "asm_throwarganyif#f2f3 = THROWARGANYIF;",
    type = THROWARGANYIF::class
) {
    override fun storeTlb(cellBuilder: CellBuilder, value: THROWARGANYIF) {
    }

    override fun loadTlb(cellSlice: CellSlice): THROWARGANYIF = THROWARGANYIF
}